from datetime import datetime

from pydantic import BaseModel


class CommentsReadDTO(BaseModel):
    comment_id: int
    author: str
    content: str
    date: datetime
