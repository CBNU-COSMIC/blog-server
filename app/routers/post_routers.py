from fastapi import APIRouter

router = APIRouter(prefix='/api/posts')


@router.post('/')
def create_post():
    return {"message": "Post created"}
