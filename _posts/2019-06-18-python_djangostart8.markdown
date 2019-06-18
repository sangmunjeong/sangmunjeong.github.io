---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발8_템플릿 동적 데이터"
date:   2019-06-18 10:18
categories: python
tags: python django
---

#Django 공부를 위한 웹 페이지를 개발8_템플릿 동적 데이터

블로그의 글들은 각각 다른 장소에 조각조각 나누어져 위치하게 됩니다.

`Post` 모델은 `models.py` 파일에, `post_list` 모델은 `views.py` 에 있죠.

그리고 앞으로 구성을 추가할수록 더욱 템플릿이 늘어나게 될 것입니다.

뷰(View)는 모델과 템플릿을 연결해주는 역할을 수행합니다.

`post_list` 를 `뷰`에서 보여주고 이를 템플릿에 전달하기 위해서, 모델을 가져와 뷰가 템플릿에서 모델을 선택하도록 만듭니다.

`blog/views.py` 파일을 열어서 `post_list` 뷰를 확인해봅니다.

```python
from django.shortcuts import render

def post_list(request):
    return render(request, 'blog/post_list.html', {})
```

전에 추가한 내용입니다.

새로 정의한 모델을 가져옵니다.

`from .models import Post`를 추가합니다.

자, 이제 원하는 쿼리셋을 만들어서 전달해주어야합니다.

우리는 시간순으로 정리한 `post`정보를 전달하고 싶으므로

`filter` 와 `published_date__lte=timezone.now()` 그리고 `order_by('published_date')를 사용합니다.

그리고 `posts` 쿼리셋을 템플릿 컨텍스트에 전달해줍니다.

최종적으론 다음과같은 코드가 됩니다.

```python
from django.shortcuts import render
from django.utils import timezone
from .models import Post

def post_list(request):
    posts = Post.objects.filter(published_date__lte=timezone.now()).order_by('published_date')
    return render(request, 'blog/post_list.html', {'posts': posts})
```

