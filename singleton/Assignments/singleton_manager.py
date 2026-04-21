"""One place to manage all singleton implementations used in experiments."""

from singletons.bill_pugh_implementation import BillPughMongoDBManager
from singletons.double_checked_locking import DoubleCheckedMongoDBManager
from singletons.eager_initialization import EagerMongoDBManager
from singletons.enum_based_singleton import EnumMongoDBManager
from singletons.lazy_initialization import LazyMongoDBManager
from singletons.synchronized_method import SynchronizedMongoDBManager


SINGLETON_IMPLEMENTATIONS = [
    ("Eager Initialization", EagerMongoDBManager, True),
    ("Lazy Initialization (basic)", LazyMongoDBManager, False),
    ("Synchronized Method", SynchronizedMongoDBManager, True),
    ("Double-Checked Locking", DoubleCheckedMongoDBManager, True),
    ("Bill Pugh Implementation", BillPughMongoDBManager, True),
    ("Enum-based Singleton", EnumMongoDBManager, True),
]


def create_instance(singleton_class):
    if singleton_class is EnumMongoDBManager:
        return EnumMongoDBManager.INSTANCE
    return singleton_class()


def reset_singleton(singleton_class):
    singleton_class.reset_instance()


def set_creation_delay(singleton_class, delay_seconds):
    if hasattr(singleton_class, "creation_delay_seconds"):
        singleton_class.creation_delay_seconds = delay_seconds
