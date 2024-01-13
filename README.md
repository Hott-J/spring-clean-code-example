# 1. 개요
- Clean Code와 DDD를 참고하여 간단한 예제를 작성

# 2. Sequence Diagram
```mermaid
sequenceDiagram
    participant 고객 as "Client"
    participant 주문컨트롤러 as "OrderController"
    participant 주문서비스 as "OrderService"
    participant 사용자리더 as "UserReader"
    participant 제품리더 as "ProductReader"
    participant 주문저장소 as "OrderStore"

    Note over 고객,주문컨트롤러: 주문 등록 프로세스
    고객->>주문컨트롤러: POST /orders (RegisterOrderRequest)
    주문컨트롤러->>주문서비스: registerOrder(command)
    주문서비스->>사용자리더: getUser(command.userId)
    사용자리더->>주문서비스: return User
    주문서비스->>제품리더: getProduct(command.productId)
    제품리더->>주문서비스: return Product
    주문서비스->>주문저장소: store(Order)
    주문저장소->>주문서비스: return stored Order
    주문서비스->>주문컨트롤러: return OrderToken
    주문컨트롤러->>고객: return CommonResponse (OrderToken)

    Note over 고객,주문컨트롤러: 주문 조회 프로세스
    고객->>주문컨트롤러: GET /orders/users/{userToken}
    주문컨트롤러->>주문서비스: retrieveOrders(userToken)
    주문서비스->>사용자리더: getUser(userToken)
    사용자리더->>주문서비스: return User
    주문서비스->>주문저장소: getAllByUser(User)
    주문저장소->>주문서비스: return List<Order>
    주문서비스->>주문컨트롤러: return List<OrderInfo>
    주문컨트롤러->>고객: return CommonResponse (List<OrderInfo>)
```

# 3. 패키지 구조 설명
![img.png](img.png)
- 각 레이어안에서의 참조는 허용
- application layer가 필요할 수 있다.
  - applicaton layer는 트랜잭션이 필요한 서비스 + 트랜잭션이 필요하지 않은 서비스의 묶음으로 정의한다.
## interfaces
- controller
- request
- mapper
## domain
- entity
- service
- command, criteria
- info
- reader, store 
## infrastructure
- JPA 구현체
- 구현 예시
![img_1.png](img_1.png)