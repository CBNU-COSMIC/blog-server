from pydantic import BaseModel


class PostsCountDTO(BaseModel):
    count: int
