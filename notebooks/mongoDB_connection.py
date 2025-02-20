from pymongo import MongoClient

#functions to connect PyCharm to MongoDB

def get_mongo_client():
    #Connect  to MongoDB and returns the client
    try:
        client = MongoClient('localhost', 27017)
        client.admin.command('ping')  # Connection test
        print("MongoDB connection established!")
        return client
    except Exception as e:
        print(f"Error to connect MongoDB: {e}")
        return None

def get_mongo_db():
    #returns the MongoDB database
    client = get_mongo_client()
    db = client['reviews']
    return db

