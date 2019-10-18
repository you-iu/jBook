-- j_book
DROP SCHEMA IF EXISTS j_book;

-- j_book
CREATE SCHEMA j_book;

-- 상품
CREATE TABLE j_book.product (
	p_no        INT          NOT NULL COMMENT '번호', -- 번호
	p_title     VARCHAR(100) NOT NULL COMMENT '제목', -- 제목
	p_name      VARCHAR(50)  NOT NULL COMMENT '저자', -- 저자
	p_price     INT          NOT NULL COMMENT '가격', -- 가격
	p_detail    TEXT         NOT NULL COMMENT '상세정보', -- 상세정보
	p_publisher VARCHAR(20)  NOT NULL COMMENT '출판사', -- 출판사
	p_regdate   DATE         NOT NULL COMMENT '출판일', -- 출판일
	p_file      VARCHAR(100) NOT NULL COMMENT '이미지', -- 이미지
	t_no        INT          NOT NULL COMMENT '분류번호' -- 분류번호
)
COMMENT '상품';

-- 상품
ALTER TABLE j_book.product
	ADD CONSTRAINT PK_product -- 상품 기본키
		PRIMARY KEY (
			p_no -- 번호
		);

ALTER TABLE j_book.product
	MODIFY COLUMN p_no INT NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 사용자
CREATE TABLE j_book.user (
	u_id       VARCHAR(20) NOT NULL COMMENT '아이디', -- 아이디
	u_name     VARCHAR(10) NOT NULL COMMENT '이름', -- 이름
	u_password VARCHAR(25) NOT NULL COMMENT '비밀번호' -- 비밀번호
)
COMMENT '사용자';

-- 사용자
ALTER TABLE j_book.user
	ADD CONSTRAINT PK_user -- 사용자 기본키
		PRIMARY KEY (
			u_id -- 아이디
		);

-- 장바구니
CREATE TABLE j_book.cart (
	c_no    INT         NOT NULL COMMENT '번호', -- 번호
	c_count INT         NOT NULL COMMENT '갯수', -- 갯수
	p_no    INT         NOT NULL COMMENT '번호2', -- 번호2
	u_id    VARCHAR(20) NOT NULL COMMENT '아이디' -- 아이디
)
COMMENT '장바구니';

-- 장바구니
ALTER TABLE j_book.cart
	ADD CONSTRAINT PK_cart -- 장바구니 기본키
		PRIMARY KEY (
			c_no -- 번호
		);

ALTER TABLE j_book.cart
	MODIFY COLUMN c_no INT NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 분류
CREATE TABLE j_book.type (
	t_no   INT         NOT NULL COMMENT '번호', -- 번호
	t_name VARCHAR(10) NOT NULL COMMENT '분류이름' -- 분류이름
)
COMMENT '분류';

-- 분류
ALTER TABLE j_book.type
	ADD CONSTRAINT PK_type -- 분류 기본키
		PRIMARY KEY (
			t_no -- 번호
		);

ALTER TABLE j_book.type
	MODIFY COLUMN t_no INT NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 상품
ALTER TABLE j_book.product
	ADD CONSTRAINT FK_type_TO_product -- 분류 -> 상품
		FOREIGN KEY (
			t_no -- 분류번호
		)
		REFERENCES j_book.type ( -- 분류
			t_no -- 번호
		);

-- 장바구니
ALTER TABLE j_book.cart
	ADD CONSTRAINT FK_product_TO_cart -- 상품 -> 장바구니
		FOREIGN KEY (
			p_no -- 번호2
		)
		REFERENCES j_book.product ( -- 상품
			p_no -- 번호
		);

-- 장바구니
ALTER TABLE j_book.cart
	ADD CONSTRAINT FK_user_TO_cart -- 사용자 -> 장바구니
		FOREIGN KEY (
			u_id -- 아이디
		)
		REFERENCES j_book.user ( -- 사용자
			u_id -- 아이디
		);
use j_book;

insert into type values 
	(null,'IT'),
	(null,'인문'),
	(null,'문학'),
	(null,'여행');

select * from type;
select * from product;

select * from user;
insert into user values ('user00','유저0','000000'),('user01','유저1','000001');
select * from product p join type t using(t_no) where p.p_no = 1;

select * from cart;
insert into cart values (null, 1, 1, 'user00');

select * from cart c join product p using(p_no) where c.u_id = 'user00';
delete from cart where c_no = 1;





