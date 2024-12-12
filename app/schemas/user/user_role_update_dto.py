from pydantic import BaseModel


class UserRoleUpdateDto(BaseModel):
    role: str
