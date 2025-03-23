# 과제 2-8(2025/03/24~2025/04/01)

[https://github.com/GSM-Backend-Dev-Class/Task.2-8](https://github.com/GSM-Backend-Dev-Class/Task.2-8)

## 💡요약

- JPA N+1 문제를 직접 해결해보며 실무 역량을 함양합니다

## ✅ 요구사항

- 개인 레포지터리가 아닌 미리 생성된 레포지터리를 이용해주세요
- 각자 코드리딩을 진행하고 구현된 **2가지 엔드포인트(`GET /api/v1/order/search`, `GET /api/v1/order/{orderId}`)에 대하여 Postman과 같은 프로그램으로 요청한 스크린샷을 Pull Request 에 첨부**하여 주세요
- `OrderJpaRepository` 내부 메서드를 보면 JPQL로 구현된 Order 검색 쿼리가 있는데 해당 쿼리를 실행 시 막대한 N+1 문제가 발생합니다,이를 **QueryDSL로 마이그레이션** 해주세요
- 기존에 `Page<>`로 구현된 페이징 처리 역시 마이그레이션 시 포함하여주세요
- Pull Request에 **어디서 N+1 문제가 일어났는지와 이를 어떻게 해결하였고 가능하다면 성능 개선을 시도하여 어떻게,왜 하였는지도 포함**하여주세요
- master 브랜치가 아니라 **task/{자신의 이름}** 형태로 브랜치를 생성하여 master 브랜치를 향하여 PR을 걸어 주세요