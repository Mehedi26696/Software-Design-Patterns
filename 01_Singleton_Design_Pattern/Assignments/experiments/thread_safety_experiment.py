
import threading

from singleton_manager import create_instance, reset_singleton, set_creation_delay


def thread_task(singleton_class, result_list, index, start_line):
    start_line.wait()
    obj = create_instance(singleton_class)
    result_list[index] = id(obj)


def run_thread_safety_analysis(singleton_name, singleton_class, thread_count=20):
    reset_singleton(singleton_class)

     
    set_creation_delay(singleton_class, 0.01)

    object_ids = [None] * thread_count
    start_line = threading.Barrier(thread_count)
    threads = []

    for index in range(thread_count):
        thread = threading.Thread(
            target=thread_task,
            args=(singleton_class, object_ids, index, start_line),
        )
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    set_creation_delay(singleton_class, 0)

    unique_objects = len(set(object_ids))

    return {
        "name": singleton_name,
        "thread_count": thread_count,
        "object_ids": object_ids,
        "unique_objects": unique_objects,
        "thread_safe": unique_objects == 1,
    }


def format_thread_safety(result):
    lines = [
        "Thread Safety Analysis Results",
        f"Threads used: {result['thread_count']}",
        f"Object IDs from threads: {result['object_ids']}",
        f"Unique object count: {result['unique_objects']}",
        f"Thread-safe in this experiment: {result['thread_safe']}",
    ]
    return lines
