---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발5_장고View"
date:   2019-06-18
categories: python
tags: python django
---
#Django 공부를 위한 웹 페이지를 개발5_장고View

자 이제 뷰(view)를 만들어 보겠습니다.

뷰(view)는 어플리케이션의 `로직` 을 넣는 부분입니다.

`모델` 에서 필요한 정보를 받아와 `템플릿` 에 전달하는 역할을 합니다.

`blog/views.py` 를 열고 내용을 살펴봅시다.

```python
from django.shortcuts import render

def post_list(request):
    return render(request, 'blog/post_list.html', {})
```

위의 내용에서는 views.py 파일에서 post_list 라는 함수(def)를 만들었습니다.

이전 에러가 해결되었을 것으므로 확인해봅니다.

TemplateDoseNotExist at/

이제 새로운 에러를 해결해봅시다.

