"""Experiment 1: Instance Validation."""

from singleton_manager import create_instance, reset_singleton


def run_instance_validation(singleton_name, singleton_class, total_objects=5):
    reset_singleton(singleton_class)

    objects = []
    for _ in range(total_objects):
        objects.append(create_instance(singleton_class))

    object_ids = [id(obj) for obj in objects]
    connection_ids = [obj.connection_id() for obj in objects]

    return {
        "name": singleton_name,
        "object_ids": object_ids,
        "connection_ids": connection_ids,
        "unique_objects": len(set(object_ids)),
        "unique_connections": len(set(connection_ids)),
        "single_instance": len(set(object_ids)) == 1,
        "single_connection": len(set(connection_ids)) == 1,
    }


def format_instance_validation(result):
    lines = [
        "Instance Validation Results",
        f"Object IDs: {result['object_ids']}",
        f"MongoDB Connection IDs: {result['connection_ids']}",
        f"Unique object count: {result['unique_objects']}",
        f"Unique connection count: {result['unique_connections']}",
        f"Single instance maintained: {result['single_instance']}",
        f"Single database connection maintained: {result['single_connection']}",
    ]
    return lines
