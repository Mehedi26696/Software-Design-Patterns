"""Bill Pugh style Singleton."""

import threading
import time

from base_mongodb_manager import BaseMongoDBManager


class BillPughMongoDBManager(BaseMongoDBManager):
    creation_delay_seconds = 0

    class Holder:
        instance = None
        lock = threading.Lock()

    def __new__(cls):
        with cls.Holder.lock:
            if cls.Holder.instance is None:
                if cls.creation_delay_seconds > 0:
                    time.sleep(cls.creation_delay_seconds)
                cls.Holder.instance = super().__new__(cls)
        return cls.Holder.instance

    @classmethod
    def reset_instance(cls):
        with cls.Holder.lock:
            if cls.Holder.instance is not None:
                cls.Holder.instance.close_connection()
            cls.Holder.instance = None
