---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발2_Django모델"
date:   2019-06-16
categories: python
tags: python django
---

#Django모델

블로그의 포스트를 저장하는 부분을 생성하도록 합니다.

객체를 생성하도록 하는데, 장고에서는 기능을 어플리케이션 단위로 관리합니다.

터미널에서 장고프로젝트 폴더로 이동후 가상환경을 시작합니다.

그다음 새로운 어플리케이션 blog를 추가해줍니다.

d

```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ python manage.py startapp blog
```

명령어를 실행하면 blog디렉토리와 그 하위 파일들이 만들어집니다.

![2019-06-16 7.52.15](/assets/post_image/image1906/2019-06-16 7.52.15.png)

생성한 어플리케이션은 장고 프로젝트에서 사용한다고 선언해주어야 합니다.

이 설정은 `mysite/settings.py`에서 `INSTALLED_APP`을 열어 `blog`를 추가하는걸로 마칠수 있습니다.

설정시 다음과같은 상태가 됩니다.

```python
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'blog',
]
```

모든 `Model` 객체는 `blog/models.py` 파일에 선언하여 모델을 만듭니다.

`blog/models.py` 파일을 열고 기본 내용을 삭제한 후 아래 코드를 추가합니다.

```
from django.db import models
from django.utils import timezone


class Post(models.Model):
    author = models.ForeignKey('auth.User', on_delete=models.CASCADE)
    title = models.CharField(max_length=200)
    text = models.TextField()
    created_date = models.DateTimeField(
            default=timezone.now)
    published_date = models.DateTimeField(
            blank=True, null=True)

    def publish(self):
        self.published_date = timezone.now()
        self.save()

    def __str__(self):
        return self.title
```



데이터 베이스에 모델을 위한 테이블을 만듭니다.

장고에 블로그앱을 만들었다는것을 알려줍니다.

```python manage.py make migrations blog```

위의 명령어를 터미널에 입력합니다.



```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ python manage.py makemigrations blog
Migrations for 'blog':
  blog/migrations/0001_initial.py
    - Create model Post
```

장고는 데이터베이스에 지금 반영할 수 있도록 마이그레이션 파일(migration file)이라는 것이 있어서 이를 이용합니다.

```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ python manage.py migrate blog
Operations to perform:
  Apply all migrations: blog
Running migrations:
  Applying blog.0001_initial... OK
```




