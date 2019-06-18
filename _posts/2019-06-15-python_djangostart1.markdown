---
layout: post
title:  "Django 공부를 위한 웹 페이지를 개발1_환경구축 및 서버실행"
date:   2019-06-15
categories: python
tags: python django
---
#  Python / Django 공부를 위한 웹 페이지를 개발1_환경구축 및 서버실행

> - 개발환경 : 
> - 맥OS 10.11.6
> - Python 3.7.3 
> - Django 2.0.13

파이썬 및 장고 실습은 [장고걸스 튜토리얼][Djangogirls]을 참조했습니다.

처음은 파이썬3 설치 및 가상환경과 장고 설치를 마쳤습니다.

장고 프로젝트를 시작하기 위해서 미리 구축한 가상환경을 시작합니다.

```
source myvenv/bin/activate
```

가상환경을 종료하는 경우는 명령어를 입력합니다.
```
deactivate
```

가상환경을 시작했다면, 새로운 프로젝트 mysite를 만들어봅시다.

```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ django-admin startproject mysite .
```

실행하면 다음과 같이 폴더가 만들어진것을 확인할 수 있습니다.

![My screenshot]({{ "/assets/post_image/image1906/2019-06-15 7.57.24.png"}})

다음은 설정을 변경합니다.

`mysite/settings.py`를 수정합니다.

정적파일 경로를 설정합니다.

STATIC_URL다음에 이하의 소스코드를 추가합니다.

```
STATIC_ROOT = os.path.join(BASE_DIR, 'static')
```

PythonAnywhere로 배포예정이므로 ALLOWED_HOSTS를 수정합니다.

```
ALLOWED_HOSTS = ['127.0.0.1', '.pythonanywhere.com']
```

다음은 데이터 베이스를 설정합니다.

기본적으로 파이썬/장고 프로젝트는 sqlite3가 기본으로 설정되어 있습니다.

변경도 가능하지만 이번엔 장고걸스튜토리얼을 따라서 진행합니다.

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
    }
}
```

설정을 확인하고 터미널에서 명령어를 입력하여 데이터베이스를 생성합니다.

`python manage.py migrate`

```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ python manage.py migrate
Operations to perform:
  Apply all migrations: admin, auth, contenttypes, sessions
Running migrations:
  Applying contenttypes.0001_initial... OK
  Applying auth.0001_initial... OK
  Applying admin.0001_initial... OK
  Applying admin.0002_logentry_remove_auto_add... OK
  Applying contenttypes.0002_remove_content_type_name... OK
  Applying auth.0002_alter_permission_name_max_length... OK
  Applying auth.0003_alter_user_email_max_length... OK
  Applying auth.0004_alter_user_username_opts... OK
  Applying auth.0005_alter_user_last_login_null... OK
  Applying auth.0006_require_contenttypes_0002... OK
  Applying auth.0007_alter_validators_add_error_messages... OK
  Applying auth.0008_alter_user_username_max_length... OK
  Applying auth.0009_alter_user_last_name_max_length... OK
  Applying sessions.0001_initial... OK
```

무사히 생성완료.



이제  웹 서버를 가동해서 웹 페이지를 확인해보겠습니다.

`(myvenv) ~/djangogirls$ python manage.py runserver`

```
(myvenv) Mac-Jui-MacBook-Pro:djangogirls mac_j$ python manage.py runserver
Performing system checks...

System check identified no issues (0 silenced).
June 15, 2019 - 11:12:36
Django version 2.0.13, using settings 'mysite.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```

위에서 나온 주소 http://127.0.0.1:8000/ 을 입력하면 생성된 웹 페이지를 확인할 수 있습니다.

![My screenshot]({{ "/assets/post_image/image1906/2019-06-15 8.12.58.png"}})


[Djangogirls]: https://tutorial.djangogirls.org/ko/
