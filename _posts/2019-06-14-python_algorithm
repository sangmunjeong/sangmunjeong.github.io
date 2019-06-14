---
layout: post
title:  "Python알고리즘 연습 완주하지못한"
date:   2019-06-14
categories: algorithm
tags: web
---

파이썬 알고리즘 학습,

완주하지 못한 선수

문제 설명
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.

```python

def solution(participant, completion):
    answer = ''
    map = {}
    for x in participant:
        if map.get(x):
            map[x] += 1
        else:
            map[x] = 1
        
    for y in completion:
        if map.get(y) > 1:
            map[y] -= 1
        else:
            del map[y]

    for key in map.keys():
        answer = key
    
    return answer
```

파이썬 함수에대한 이해도 부족.
파이썬 딕셔너리 학습 필요성을 느꼈다.
