 

import os
import threading

from dotenv import load_dotenv
from pymongo import MongoClient
from pymongo.server_api import ServerApi


load_dotenv()


class BaseMongoDBManager:
     

    _init_lock = threading.Lock()

    def __init__(self):
        with BaseMongoDBManager._init_lock:
            if getattr(self, "_initialized", False):
                return

            self.client = None
            self.database = None
            self._connection_lock = threading.Lock()
            self._initialized = True
            self._connect()

    def _connect(self):
        with self._connection_lock:
            if self.client is not None:
                return

            mongodb_uri = os.getenv("MONGODB_URI")
            database_name = os.getenv("MONGODB_DB", "singleton_db")

            if not mongodb_uri:
                raise ValueError("MONGODB_URI is missing in the .env file.")

            self.client = MongoClient(
                mongodb_uri,
                server_api=ServerApi("1"),
                serverSelectionTimeoutMS=3000,
            )
            self.database = self.client[database_name]

    def get_database(self):
        self._connect()
        return self.database

    def get_collection(self, name):
        return self.get_database()[name]

    def close_connection(self):
        if self.client is not None:
            self.client.close()
            self.client = None
            self.database = None

    def connection_id(self):
        self._connect()
        return id(self.client)
