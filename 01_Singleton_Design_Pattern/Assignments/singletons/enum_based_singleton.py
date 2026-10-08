 

from enum import Enum

from base_mongodb_manager import BaseMongoDBManager


class MongoDBManagerForEnum(BaseMongoDBManager):
    pass


class EnumMongoDBManager(Enum):
    INSTANCE = MongoDBManagerForEnum()

    @classmethod
    def reset_instance(cls):
        cls.INSTANCE.close_connection()

    def get_database(self):
        return self.value.get_database()

    def get_collection(self, name):
        return self.value.get_collection(name)

    def close_connection(self):
        self.value.close_connection()

    def connection_id(self):
        return self.value.connection_id()
