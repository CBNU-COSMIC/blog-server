from app.models.user import User
from datetime import datetime


class Post:
    def __init__(self, id: str, title: str, attribute: str, content: str, writer: User, board: str,
                 created_at: datetime):
        self.id = id
        self.title = title
        self.attribute = attribute
        self.content = content
        self.writer = writer
        self.board = board
        self.created_at = created_at

    def __repr__(self):
        return (f"Post(id={self.id!r}, title={self.title!r}, attribute={self.attribute!r}, content={self.content!r},"
                f" writer={self.writer!r}, board={self.board!r}, created_at={self.created_at!r})")
