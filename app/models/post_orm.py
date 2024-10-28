from sqlalchemy import Column, String, DateTime, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from datetime import datetime

Base = declarative_base()

class PostORM(Base):
    __tablename__ = 'Post'

    id = Column(String, primary_key=True)
    title = Column(String, nullable=False)
    attribute = Column(String)
    content = Column(String, nullable=False)
    user_id = Column(String, ForeignKey('Member.id'), nullable=False)
    board = Column(String)
    created_at = Column(DateTime)

    def __init__(self, id: str, title: str, attribute: str, content: str, user_id: str, board: str, created_at: datetime):
        self.id = id
        self.title = title
        self.attribute = attribute
        self.content = content
        self.user_id = user_id
        self.board = board
        self.created_at = created_at
