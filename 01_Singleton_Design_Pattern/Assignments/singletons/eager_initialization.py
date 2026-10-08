 

from base_mongodb_manager import BaseMongoDBManager


class EagerMongoDBManager(BaseMongoDBManager):
    _instance = None

    def __new__(cls):
        return cls._instance

    @classmethod
    def reset_instance(cls):
        if cls._instance is not None:
            cls._instance.close_connection()

        cls._instance = object.__new__(cls)
        BaseMongoDBManager.__init__(cls._instance)


EagerMongoDBManager.reset_instance()
