## 핵심 문제해결 전략
 1. 다수의 서버 다수의 인스턴스로 동작
    - db lock 사용으로 해결
    - Token 중복 방지를 위해 DB에서 중복 체크 해야함
 2. 다량의 트래픽 처리
    - <del>reactive webFlux 사용으로 해결
    - DB SELECT 조건을 최소화하고 서버에서 처리하기