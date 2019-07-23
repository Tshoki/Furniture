set names utf8;
set foreign_key_checks = 0;

drop database if exists jaguar;
create database if not exists jaguar;

use jaguar;

create table user_info(
id int primary key not null auto_increment,
user_id varchar(16) unique not null,
password varchar(16) not null,
family_name varchar(32) not null,
first_name varchar(32) not null,
family_name_kana varchar(32) not null,
first_name_kana varchar(32) not null,
sex tinyint default 0,
email varchar(32),
status tinyint not null default 0,
logined tinyint not null default 0,
regist_date datetime,
update_date datetime
);

insert into user_info values
(1,"guest", "guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"internous.guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"internous.guest2@gmail.com",0,0,now(),now()),
(3,"guest3","guest3","テスト","太郎3","てすと","たろう3",0,"guest3@gmail.com",0,0,now(),now()),
(4,"guest4","guest4","テスト","太郎4","てすと","たろう4",0,"guest4@gmail.com",0,0,now(),now()),
(5,"guest5","guest5","テスト","太郎5","てすと","たろう5",0,"guest5@gmail.com",0,0,now(),now()),
(6,"guest6","guest6","テスト","太郎6","てすと","たろう6",0,"guest6@gmail.com",0,0,now(),now()),
(7,"guest7","guest7","テスト","太郎7","てすと","たろう7",0,"guest7@gmail.com",0,0,now(),now()),
(8,"guest8","guest8","テスト","太郎8","てすと","たろう8",0,"guest8@gmail.com",0,0,now(),now()),
(9,"guest9","guest9","テスト","太郎9","てすと","たろう9",0,"guest9@gmail.com",0,0,now(),now()),
(10,"guest10","guest10","テスト","太郎10","てすと","たろう10",0,"guest10@gmail.com",0,0,now(),now()),
(11,"guest11","guest11","テスト","太郎11","てすと","たろう11",0,"guest11@gmail.com",0,0,now(),now()),
(12,"guest12","guest12","テスト","太郎12","てすと","たろう12",0,"guest12@gmail.com",0,0,now(),now());

create table m_category(
id int primary key not null auto_increment,
category_id int unique not null,
category_name varchar(20) unique not null,
category_description varchar(100),
insert_date datetime not null,
update_date datetime
);

insert into m_category values
(1,1,"全てのカテゴリー","本、家電・パソコン、おもちゃ・ゲーム、CD全てのカテゴリーが対象になります",now(),now()),
(2,2,"本","本に関するカテゴリーが対象になります",now(),now()),
(3,3,"家電・パソコン","家電・パソコンに関するカテゴリーが対象になります",now(),now()),
(4,4,"おもちゃ・ゲーム","おもちゃ・ゲームに関するカテゴリーが対象になります",now(),now()),
(5,5,"CD","CDに関するカテゴリーが対象になります",now(),now());

create table product_info(
id int primary key auto_increment not null,
product_id int unique not null,
product_name varchar(100) unique not null,
product_name_kana varchar(100) unique not null,
product_description varchar(255),
category_id int not null,
price int not null,
image_file_path varchar(100) not null,
image_file_name varchar(50) not null,
release_date datetime,
release_company varchar(50),
status tinyint default 1,
regist_date datetime,
update_date datetime,
foreign key (category_id) references m_category (category_id)
);

insert into product_info values
(1,1,"同人誌","どうじんし","春コミを逃した人たちへ",2,500,"./images","book_1.jpg",now(),"株式会社ゴマ",1,now(),now()),
(2,2,"法律書","ほうりつしょ","物理的な武器にも使えます",2,1500,"./images","book_2.jpg",now(),"株式会社ゴマ",1,now(),now()),
(3,3,"名言集","めいげんしゅう","人生に迷った時はコレ",2,650,"./images","book_3.jpg",now(),"株式会社ゴマ",1,now(),now()),

(6,6,"冷蔵庫","れいぞうこ","220L･三人家族用",3,140000,"./images","home_appliances_1.jpg",now(),"株式会社青葉",1,now(),now()),
(7,7,"テレビ","てれび","白黒テレビ",3,200000,"./images","home_appliances_2.jpg",now(),"株式会社青葉",1,now(),now()),
(8,8,"洗濯機","せんたくき","ドラム式洗濯機･節水仕様",3,60000,"./images","home_appliances_3.jpg",now(),"株式会社青葉",1,now(),now()),
(9,9,"ノートパソコン","のーとぱそこん","軽量型･大学デビューに",3,100000,"./images","home_appliances_4.jpg",now(),"株式会社青葉",1,now(),now()),

(11,11,"テレビゲーム機","てれびげーむき","HDMI対応･コントローラー四つ付属",4,8000,"./images","hobby_1.jpg",now(),"株式会社トマト",1,now(),now()),
(12,12,"オセロ","おせろ","脳トレ用に",4,1000,"./images","hobby_2.jpg",now(),"株式会社トマト",1,now(),now()),
(13,13,"VRゴーグル","ぶいあーるごーぐる","ホラーゲームで恐怖を味わおう！",4,40000,"./images","hobby_3.jpg",now(),"株式会社トマト",1,now(),now()),
(14,14,"ボードゲーム","ぼーどげーむ","トークを楽しみながら遊べる",4,2000,"./images","hobby_4.jpg",now(),"株式会社トマト",1,now(),now()),
(15,15,"虫型ラジコン","むしがたらじこん","悪戯に使えます！人が多いところでは注意",4,3000,"./images","hobby_5.jpg",now(),"株式会社トマト",1,now(),now()),
(16,16,"CDプレイヤー","しーでぃーぷれいやー","最近はあまり見ない品",5,1500,"./images","cd_1.jpg",now(),"株式会社NASU",1,now(),now());

create table cart_info(
id int primary key not null auto_increment,
user_id varchar(16) not null,
product_id int not null,
product_count int not null,
regist_date datetime,
update_date datetime,
foreign key (product_id) references product_info (product_id)
);

create table destination_info(
id int primary key not null auto_increment,
user_id varchar(16) not null,
family_name varchar(32) not null,
first_name varchar(32) not null,
family_name_kana varchar(32) not null,
first_name_kana varchar(32) not null,
email varchar(32) not null,
tel_number varchar(13),
user_address varchar(50) not null,
regist_date datetime,
update_date datetime,
foreign key (user_id) references user_info (user_id)
);

insert into destination_info value
(1,"guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー","internous.guest@gmail.com","09000000000","東京都千代田区霞が関3-6-15",now(),now());

create table purchase_history_info(
id int primary key auto_increment not null,
user_id varchar(16) not null,
product_id int not null,
product_count int,
price int not null,
destination_id int,
regist_date datetime,
update_date datetime,
foreign key (user_id) references user_info (user_id),
foreign key (product_id) references product_info (product_id)
);

