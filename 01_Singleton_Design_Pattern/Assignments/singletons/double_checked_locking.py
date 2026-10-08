 

import threading
import time

from base_mongodb_manager import BaseMongoDBManager


class DoubleCheckedMongoDBManager(BaseMongoDBManager):
    _instance = None
    _lock = threading.Lock()
    creation_delay_seconds = 0

    def __new__(cls):
        if cls._instance is None:
            if cls.creation_delay_seconds > 0:
                time.sleep(cls.creation_delay_seconds)
            with cls._lock:
                if cls._instance is None:
                    cls._instance = super().__new__(cls)
        return cls._instance

    @classmethod
    def reset_instance(cls):
        with cls._lock:
            if cls._instance is not None:
                cls._instance.close_connection()
            cls._instance = None
