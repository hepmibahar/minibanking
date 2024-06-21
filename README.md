
# Minibanking

Bu proje, basit bir bankacılık uygulaması olarak tasarlanmıştır. Kullanıcıların hesap yönetimi, para transferi ve işlem geçmişi gibi temel bankacılık işlevlerini gerçekleştirmelerine olanak tanır.

## Proje Yapısı

```
minibanking
│
├── .idea/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── tr/
│                   └── minibanking/
│                       ├── controller/
│                       │   ├── AccountController.java
│                       │   ├── TransactionController.java
│                       │   └── UserController.java
│                       ├── dto/
│                       │   ├── AccountDto.java
│                       │   ├── TransactionDto.java
│                       │   └── UserDto.java
│                       ├── entity/
│                       │   ├── Account.java
│                       │   ├── BaseEntity.java
│                       │   ├── Transaction.java
│                       │   └── User.java
│                       ├── enums/
│                       │   ├── Message.java
│                       │   └── TransactionStatus.java
│                       ├── exception/
│                       │   └── GlobalExceptionHandler.java
│                       ├── generator/
│                       │   └── IbanGenerator.java
│                       ├── mapper/
│                       │   ├── AccountMapper.java
│                       │   └── UserMapper.java
│                       ├── model/
│                       │   ├── ApiResponse.java
│                       │   ├── JwtRequest.java
│                       │   └── JwtResponse.java
│                       ├── repository/
│                       │   ├── AccountRepository.java
│                       │   ├── TransactionRepository.java
│                       │   └── UserRepository.java
│                       ├── scripts/
│                       │   └── all-scripts.sql
│                       ├── security/
│                       │   ├── JwtRequestFilter.java
│                       │   ├── JwtTokenUtil.java
│                       │   └── SecurityConfig.java
│                       ├── service/
│                       │   ├── AccountService.java
│                       │   ├── JwtUserDetailsService.java
│                       │   ├── TransactionService.java
│                       │   └── UserService.java
│                       └── MinibankingApplication.java
├── resources/
├── test/
├── target/
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Kurulum

Projeyi çalıştırmak için aşağıdaki adımları izleyin:

1. **Projeyi Klonlama**:
   ```bash
   git clone https://github.com/hepmibahar/minibanking.git
   cd minibanking
   ```

2. **Bağımlılıkları Yükleme**:
   Maven kullanarak proje bağımlılıklarını yükleyin:
   ```bash
   ./mvnw clean install
   ```

3. **Veritabanı Kurulumu**:
   `all-scripts.sql` dosyasındaki SQL komutlarını kullanarak veritabanını oluşturun.

4. **Docker Kullanarak Çalıştırma**:
   Docker ve Docker Compose kullanarak projeyi çalıştırın:
   ```bash
   docker-compose up --build
   ```

## Kullanım

Proje çalıştırıldıktan sonra aşağıdaki uç noktaları kullanabilirsiniz:

- **Hesap Yönetimi**:
  - `/api/accounts` - Hesap oluşturma, güncelleme ve silme işlemleri.
  
- **Para Transferi**:
  - `/api/transactions` - Para transferi işlemleri.
  
- **Kullanıcı Yönetimi**:
  - `/api/users` - Kullanıcı oluşturma, güncelleme ve silme işlemleri.

## Güvenlik

JWT (JSON Web Token) kullanılarak güvenlik sağlanmıştır. Güvenli uç noktalara erişim için JWT token gereklidir.

## Katkıda Bulunma

Katkıda bulunmak isterseniz, lütfen bir çekme isteği (pull request) oluşturun. Her türlü katkı değerlidir.

## Lisans

Bu proje MIT lisansı ile lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına bakın.
