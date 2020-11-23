## 핵심 문제해결 전략
 1. 다수의 서버 다수의 인스턴스로 동작
    - db lock 사용으로 해결
 2. 다량의 트래픽 처리
    - <del>reactive webFlux 사용으로 해결