---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발7_Django ORM과 QuerySets"
date:   2019-06-17
categories: python
tags: python django
---
#Django 공부를 위한 웹 페이지를 개발7_Django ORM과 QuerySets

쿼리셋이란 무엇일까요.

QuerySet은 전달받는 모델의 객체 목록입니다. 데이터베이스로부터 데이터를 읽고, 필터나 정렬기능을 사용합니다.

Django Shell

터미널에서 다음과 같이 입력해 봅시다.

```
python manage.py shell
```

다음과 같은 화면을 볼 수 있습니다.

```
(InteractiveConsole)
>>>
```

객체를 조회하기 위해서는 해당 글을 불러와야합니다.(import)
```
>>>from blog.models import Post
```

이것으로 `Post` 모델을 `blog.models` 에서 불러왔습니다. 이제 모든 글을 출력해 봅니다.

```
>>> Post.objects.all()
<QuerySet [<Post: my post title>]>
```

객체 생성.

데이터베이스에서 새 글 객체를 저장하는 방법에 대해서 알아 보겠습니다.

```
>>> Post.objects.create*author=me, title='Sample title', text='text')
```

실행 되지 않을겁니다. `me`를 가져와야 합니다.

```
>>> from django.contrib.auth.models import User
```
User 모델을 불러왔습니다.
한번 뭐가 있는지 확인해봅시다.

```
>>> User.objects.all()
<QuerySet [<User: admin>]>
```

`superuser`로 등록한 사용자가 보입니다. 이 사용자의 인스턴스(instance)를 가져오겠습니다.

```
>>> me = User.objcets.get(username='admin')
```

`me`를 가져왔으니 이제 객제를 다시 생성해 봅니다.

```
>>> Post.objects.create*author=me, title='Sample title', text='text')
```

정상적으로 실행되었습니다.

확인을 해봅시다.

```
>>> Post.objects.all()
<QuerySet [<Post: my post title>, <Post: Sample title>] >
```

검색과 추가는 문제없이 가능했습니다.

이제 필터에 대해서 공부해 보겠습니다.

필터기능을 사용할 경우 `Post.objects.all()` 에서 `all` 대신 `filter` 를 사용합니다.

예시로 `작성자(author)`가 `나(me)`인 경우를 검색해보겠습니다.

```
>>> Post.objects.filter(author=me)
[<Post: my post title>, <Post: Sample title>]
```

검색 조건을 `제목(title)`에 대해서 정하고싶다면!

```
>>> Post.objects.filter(title__contains='Sample')
[<Post: Sample title>]
```

위와 같은 방식으로 검색 할 수 있습니다.


게시된 글의 목록을 불러올 수도 있습니다.

`게시일(published_date)`로 필터링해서 가져옵니다.

```
>>> from django.utils import timezone
>>> Post.objects.filter(published_date__lte=timezone.now())
[]
```

콘솔에서 추가한 게시물이 보이지 않습니다.

해결해 보겠습니다.

```
>>> post = Post.objects.get(title="Sample title")
```

`post` 인스턴스를 얻어옵니다.

```
>>> post.publish()
```

`publish()` 메서드로 게시합니다.

다시한번 목록을 검색해보면 이번엔 정상으로 출력되는것을 확인해볼 수 있습니다.


정렬

쿼리셋으로 객체 목록을 정렬할 수 있습니다.

```
>>> Post.objects.order_by('created_date')
[<Post: Sample title>, <Post: Post number 2>, <Post: My 3rd post!>, <Post: 4th title of post>]
```

옵션`-`를 추가하면 내림차순 정렬도 가능합니다.

```
>>> Post.objects.order_by('-created_date')
[<Post: 4th title of post>,  <Post: My 3rd post!>, <Post: Post number 2>, <Post: Sample title>]
```

쿼리셋 연결

쿼리셋을 연결할 수도 있습니다.

```
>>> Post.objects.filter(published_date__lte=timezone.now()).order_by('published_date')
```

SQL에서 join을 하는것처럼 조건들을 연결하네요. 여러가지로 유용할 듯 합니다.

자 이제 쉘을 종료하고 다음 내용을 진행합시다.

```
>>> exit()
```

