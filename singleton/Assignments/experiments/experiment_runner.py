"""Runs all experiments and saves the data in a text file."""

from pathlib import Path

from experiments.instance_validation_experiment import (
    format_instance_validation,
    run_instance_validation,
)
from experiments.performance_comparison_experiment import (
    format_performance_comparison,
    run_performance_comparison,
)
from experiments.thread_safety_experiment import (
    format_thread_safety,
    run_thread_safety_analysis,
)
from singleton_manager import SINGLETON_IMPLEMENTATIONS


OUTPUT_FILE = Path("experiment_results.txt")


def add_section(lines, title):
    lines.append("")
    lines.append("=" * 70)
    lines.append(title)
    lines.append("=" * 70)


def run_all_experiments():
    report_lines = [
        "Singleton Pattern with MongoDB - Experiment Results",
        "This file is generated from the Python experiment code.",
        "Use these results in the 2-4 page data-driven report.",
    ]

    instance_results = []
    thread_results = []
    performance_results = []

    for singleton_name, singleton_class, expected_thread_safe in SINGLETON_IMPLEMENTATIONS:
        add_section(report_lines, singleton_name)

        instance_result = run_instance_validation(singleton_name, singleton_class)
        thread_result = run_thread_safety_analysis(singleton_name, singleton_class)
        performance_result = run_performance_comparison(
            singleton_name,
            singleton_class,
        )

        instance_results.append(instance_result)
        thread_results.append(thread_result)
        performance_results.append(performance_result)

        report_lines.extend(format_instance_validation(instance_result))
        report_lines.append("")
        report_lines.extend(format_thread_safety(thread_result))
        report_lines.append(f"Expected thread-safe: {expected_thread_safe}")
        report_lines.append("")
        report_lines.extend(format_performance_comparison(performance_result))

    add_summary(report_lines, instance_results, thread_results, performance_results)

    final_text = "\n".join(report_lines)
    OUTPUT_FILE.write_text(final_text, encoding="utf-8")
    print(final_text)
    print("")
    print(f"Results saved to: {OUTPUT_FILE.resolve()}")


def add_summary(report_lines, instance_results, thread_results, performance_results):
    add_section(report_lines, "Comparative Summary Based on Results")

    report_lines.append("Instance Validation Summary")
    for result in instance_results:
        report_lines.append(
            f"{result['name']}: unique_objects={result['unique_objects']}, "
            f"unique_connections={result['unique_connections']}"
        )

    report_lines.append("")
    report_lines.append("Multithreading Summary")
    for result in thread_results:
        report_lines.append(
            f"{result['name']}: thread_safe={result['thread_safe']}, "
            f"unique_objects={result['unique_objects']}"
        )

    report_lines.append("")
    report_lines.append("Performance Summary")
    for result in performance_results:
        report_lines.append(
            f"{result['name']}: "
            f"avg_init={result['average_initialization_time']:.8f}s, "
            f"avg_access={result['average_access_time']:.12f}s"
        )

    fastest_init = min(
        performance_results,
        key=lambda item: item["average_initialization_time"],
    )
    fastest_access = min(
        performance_results,
        key=lambda item: item["average_access_time"],
    )

    report_lines.append("")
    report_lines.append("Observed Findings")
    report_lines.append(
        f"Fastest initialization in this run: {fastest_init['name']}"
    )
    report_lines.append(f"Fastest repeated access in this run: {fastest_access['name']}")
    report_lines.append(
        "Implementations that produced multiple objects in the thread test: "
        + get_failed_thread_names(thread_results)
    )


def get_failed_thread_names(thread_results):
    failed_names = []
    for result in thread_results:
        if not result["thread_safe"]:
            failed_names.append(result["name"])

    if not failed_names:
        return "None"
    return ", ".join(failed_names)
