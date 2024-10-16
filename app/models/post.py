from datetime import datetime


class Post:
    def __init__(self, id: str, title: str, attribute: str, content: str, user_id: str, board: str,
                 created_at: datetime):
        self._validate_title(title=title)

        self.__id = id
        self.__title = self._make_valid_title(title)
        self.__attribute = attribute
        self.__content = content
        self.__user_id = user_id
        self.__board = board
        self.__created_at = created_at

    @property
    def id(self):
        return self.__id

    @property
    def title(self):
        return self.__title

    @property
    def attribute(self):
        return self.__attribute

    @property
    def content(self):
        return self.__content

    @property
    def user_id(self):
        return self.__user_id

    @property
    def board(self):
        return self.__board

    @property
    def created_at(self):
        return self.__created_at

    def __repr__(self):
        return (f"Post(id={self.__id!r}, title={self.__title!r}, attribute={self.__attribute!r}, content={self.__content!r},"
                f" writer={self.__user_id!r}, board={self.__board!r}, created_at={self.__created_at!r})")

    def _validate_title(self, title: str) -> None:
        """
        글 제목의 유효성을 검사합니다.
        """
        if not title:
            raise ValueError(f"현재 제목: {title}")

    def _make_valid_title(self, title: str) -> str:
        """
        글 제목을 유효한 제목으로 변경합니다.
        """
        return title.replace('<', '&lt;').replace('>', '&gt;')