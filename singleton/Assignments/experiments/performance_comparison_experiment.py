"""Experiment 3: Performance Comparison."""

import time

from singleton_manager import create_instance, reset_singleton


def measure_initialization_time(singleton_class, runs=10):
    times = []

    for _ in range(runs):
        start = time.perf_counter()
        reset_singleton(singleton_class)
        obj = create_instance(singleton_class)
        obj.get_database()
        end = time.perf_counter()
        times.append(end - start)

    return sum(times) / len(times)


def measure_access_time(singleton_class, repeated_calls=10000):
    reset_singleton(singleton_class)
    create_instance(singleton_class)

    start = time.perf_counter()
    for _ in range(repeated_calls):
        create_instance(singleton_class)
    end = time.perf_counter()

    return end - start


def run_performance_comparison(
    singleton_name,
    singleton_class,
    initialization_runs=10,
    repeated_calls=10000,
):
    average_initialization_time = measure_initialization_time(
        singleton_class,
        initialization_runs,
    )
    total_access_time = measure_access_time(singleton_class, repeated_calls)

    return {
        "name": singleton_name,
        "initialization_runs": initialization_runs,
        "repeated_calls": repeated_calls,
        "average_initialization_time": average_initialization_time,
        "total_access_time": total_access_time,
        "average_access_time": total_access_time / repeated_calls,
    }


def format_performance_comparison(result):
    lines = [
        "Performance Comparison Results",
        f"Initialization runs: {result['initialization_runs']}",
        "Average initialization time: "
        f"{result['average_initialization_time']:.8f} seconds",
        f"Repeated access calls: {result['repeated_calls']}",
        f"Total access time: {result['total_access_time']:.8f} seconds",
        f"Average access time: {result['average_access_time']:.12f} seconds",
    ]
    return lines
