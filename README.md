## 졸업 작품 프로젝트(Detect Computer Attack)

### Requirements

해당 Repositories 생성할 때 사용한 버전

- docker == 20.10.21
- docker-compose == 2.17.2

### Setting

`Script` 폴더에서 아래 명령어 실행(다른 폴더에서 실행하면 경로 에러 발생!)

```bash
chmod +x <스크립트이름>
./<스크립트이름>
```

- setting.sh

`Flask`, `MairaDB`, `SpringBoot` port 설정 및 `MariaDB` 설정

- blacklist.sh

`iptables` 명령어를 이용한 사용자 ip 차단 스크립트

- mariadb_backup.sh

mariadb 데이터 백업 스크립트

- mariadb_scheduling.sh

`mariadb_backup.sh` 스크립트를 이용한 crontab 적용(현재 매주 월요일 0시 0분에 백업으로 설정)

### Run

실행과 종료는 docker-compose를 실행하면 됨.

아래는 간단한 예시

- 실행

```bash
docker-compose up
```

- 백그라운드 실행

```bash
docker-compose up -d
```

- 종료

```bash
docker-compose down
```

- 실행중인 리스트

```bash
docker-compose ps
```
