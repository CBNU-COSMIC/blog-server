class comment:

    def __init__(self, id: str, writer: str, content: str, parent_id: str, post_date: str):
        self.__id = id
        self.__writer = writer
        self.__content = content
        self.__parent_id = parent_id
        self.__post_date = post_date


    @property
    def id(self) -> str:
        return self.__id

    @property
    def writer(self) -> str:
        return self.__writer

    @property
    def content(self) -> str:
        return self.__content

    @property
    def parent_id(self) -> str:
        return self.__parent_id

    @property
    def post_date(self) -> str:
        return self.__post_date

