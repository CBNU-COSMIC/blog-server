from sqlalchemy import Column, String, DateTime, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
import datetime

Base = declarative_base()

class CommentORM(Base):
    __tablename__ = 'Comment'

    id = Column(String, primary_key=True)
    user_id = Column(String, ForeignKey('Member.id'), nullable=False)
    content = Column(String, ForeignKey('Post.id'))
    parent_id = Column(String, ForeignKey('Comment.id'))  # 대댓글을 위한 자기 참조 외래 키
    comment_date = Column(DateTime,)

    def __init__(self, id: str, user_id: str, content: str, parent_id: str, comment_date: datetime):
        self.id = id
        self.user_id = user_id
        self.content = content
        self.parent_id = parent_id
        self.comment_date = comment_date
