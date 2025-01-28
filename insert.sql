SET foreign_key_checks=1;

USE team2411a;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES 
('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('家具','リビングインテリア'),
('家電','リビング家電'),
('寝具','寝室用品');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company)VALUES
('ソファ','そふぁ','2人掛けソファ',1,25000,'/img/sofa.jpg','2020/01/05','ニトリ'),
('テーブル','てーぶる','4人用テーブル',1,20000,'/img/table.jpg','2020/01/15','IKEA'),
('スピーカー','すぴーかー','小型スピーカー',2,10000,'/img/speaker.jpg','2020/01/25','アイリスオーヤマ'),
('照明','しょうめい','間接照明',2,12000,'/img/lighting.jpg','2020/02/05','IKEA'),
('ベッド','べっど','シングルベッド',3,18000,'/img/bed.jpg','2020/02/15','ニトリ'),
('枕','まくら','低反発枕',3,3500,'/img/pillow.jpg','2020/02/25','IKEA'),
('布団','ふとん','シングル用',3,5000,'/img/comforter.jpg','2020/03/05','西川'); 