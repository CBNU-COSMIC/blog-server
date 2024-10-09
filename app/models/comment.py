import datetime


class Comment:

    def __init__(self, id: str, user_id: str, content: str, parent_id: str, comment_date: datetime):
        self.__id = id
        self.__user_id = user_id
        self.__content = content
        self.__parent_id = parent_id
        self.__comment_date = comment_date

    @property
    def id(self) -> str:
        return self.__id

    @property
    def user_id(self) -> str:
        return self.__user_id

    @property
    def content(self) -> str:
        return self.__content

    @property
    def parent_id(self) -> str:
        return self.__parent_id

    @property
    def comment_date(self) -> str:
        return self.__comment_date

    def __repr__(self):
        return (f"Comment(id={self.__id!r}, user_id={self.__user_id!r}, content={self.__content!r},"
                f" parent_id={self.__parent_id!r}, comment_date={self.__comment_date!r})")

    def update_comment_date(self) -> None:
        self.__comment_date = datetime.datetime.now()