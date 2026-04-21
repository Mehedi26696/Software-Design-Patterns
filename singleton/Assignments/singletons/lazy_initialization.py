"""Basic Lazy Initialization Singleton."""

import time

from base_mongodb_manager import BaseMongoDBManager


class LazyMongoDBManager(BaseMongoDBManager):
    _instance = None
    creation_delay_seconds = 0

    def __new__(cls):
        if cls._instance is None:
            if cls.creation_delay_seconds > 0:
                time.sleep(cls.creation_delay_seconds)
            cls._instance = super().__new__(cls)
        return cls._instance

    @classmethod
    def reset_instance(cls):
        if cls._instance is not None:
            cls._instance.close_connection()
        cls._instance = None
