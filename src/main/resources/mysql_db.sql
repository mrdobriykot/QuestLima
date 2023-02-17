CREATE DATABASE  IF NOT EXISTS `quest_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `quest_db`;
-- MariaDB dump 10.19  Distrib 10.9.4-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: quest_db
-- ------------------------------------------------------
-- Server version	10.9.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `answer` varchar(300) DEFAULT NULL,
  `next_question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_next_question_id_idx` (`next_question_id`),
  KEY `fk_answers_question_id_idx` (`question_id`),
  CONSTRAINT `fk_answers_question_id` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_next_question_id` FOREIGN KEY (`next_question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES
(1,1,'Джентльмены удачи ',7),
(2,1,'Ирония судьбы, или С лёгким паром ',2),
(3,1,'Чародеи',7),
(4,2,'Двенадцать месяцев ',7),
(5,2,'Вечера на хуторе близ Диканьки ',7),
(6,2,'Морозко',3),
(7,3,'Четыре комнаты ',7),
(8,3,'Отпуск по обмену ',7),
(9,3,'Один дома 2',4),
(10,4,'Карнавальная ночь',5),
(11,4,'За двумя зайцами',7),
(12,4,'Старый Новый год',7),
(13,5,'Четыре рождества',7),
(14,5,'С Новым годом, мамы!',7),
(15,5,'Ёлки',6),
(16,8,'Игорь',9),
(17,8,'Олег',10),
(18,8,'Андрей',9),
(19,9,'Вернуться назад',8),
(20,10,'Это было слишком легко',11),
(21,11,'25',12),
(22,11,'125',12),
(23,11,'625',12),
(24,11,'Нисколько',13),
(25,13,'Вперёд к победе',14);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest_states`
--

DROP TABLE IF EXISTS `quest_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quest_states` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest_states`
--

LOCK TABLES `quest_states` WRITE;
/*!40000 ALTER TABLE `quest_states` DISABLE KEYS */;
INSERT INTO `quest_states` VALUES
(1,'PLAYING'),
(2,'WIN'),
(3,'LOSE');
/*!40000 ALTER TABLE `quest_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `quest_id` bigint(15) NOT NULL,
  `image` varchar(100) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `quest_state_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quest_state_id_idx` (`quest_state_id`),
  KEY `fk_questions_quest_id_idx` (`quest_id`),
  CONSTRAINT `fk_quest_state_id` FOREIGN KEY (`quest_state_id`) REFERENCES `quest_states` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_quest_id` FOREIGN KEY (`quest_id`) REFERENCES `quests` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES
(1,1,'z1ljvb9btw8e0neusmkq1.jpg','Начнем, из какого фильма этот кадр?',1),
(2,1,'smzllk6nwnurhtvmlcei1.jpg','Из какой картины эта девушка?',1),
(3,1,'xqb9dd57bfnvastcmof71.jpg','Откуда данный кадр?',1),
(4,1,'mmfvf6oiokj5lpz6wcq51.jpg','Узнали этот фильм?',1),
(5,1,'xdfp1rs2ic8tlipinz2n1.jpg','Откуда данный кадр?',1),
(6,1,'win.png','У вас отличные знания новогодних фильмов!',2),
(7,1,'lose.png','Стоит пересмотреть новогодние фильмы',3),
(8,2,'1599802976_11.jpg','Игорь, Олег и Андрей предложили подвезти вас до дома. Двое из них хотят вас убить. Один — нет. Кроме того, вам известно, что как минимум один из этой троицы всегда врет (и это никак не связано с тем, хочет ли этот человек вас убить). Вам нужно выбрать, с кем поехать, чтобы добраться домой живым. Игорь говорит, что Олег и Андрей говорят правду. Олег заявляет: «Если хочешь жить, выбери Игоря или Андрея». Андрей советует не выбирать Олега, если вам хочется жить. Кого из троих нужно выбрать?',1),
(9,2,'1599802976_11.jpg','К сожалению это был неверный ответ, попробуйте ещё подумать и у вас всё получится',1),
(10,2,'1599802976_11.jpg','Отлично, это правильный ответ. Объяснение: <br>Ехать нужно с Олегом. Если Игорь говорит правду, тогда Олег и Андрей тоже говорят правду. Мы знаем из условия, что как минимум один из троих лжет. Значит, Игорь не может говорить правду. Далее у нас есть три возможных ситуации: Олег и Андрей — оба лжецы; Олег — лжец, а Андрей говорит правду; Андрей — лжец, а Олег говорит правду. Первый вариант возможен, и тогда ехать нужно с Олегом (он лжет, советуя выбрать любого, но не его самого, а Андрей лжет, не советуя выбирать Олега). Во втором случае у нас явное противоречие: если Олег лжет, значит, ехать нужно с ним. А Андрей, который говорит правду, не советует выбирать Олега. В третьем случае мы тоже приходим к противоречию: лжец Андрей советует не выбирать Олега (значит, выбрать нужно как раз Олега), а правдивый Олег советует выбрать Игоря или Андрея. Так мы приходим к тому, что ехать можно только с Олегом.',1),
(11,2,'sherlok_holms1.jpg','В конференции людей высокого роста участвует 25 человек. Каждый пожал руку только тому, кто ниже его ростом. Сколько всего рукопожатий состоялось?',1),
(12,2,'lose.png','Ну что ж, повезёт в следующий раз, квест не пройден',3),
(13,2,'sherlok_holms1.jpg','На этой конференции никто никому рук не пожмет. Ведь пожимать руки можно только тем, кто ниже. Тогда тот, кто ниже, нарушит правило.',1),
(14,2,'win.png','У Вас отличное логическое мышление, квест пройден! =)',2);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quests`
--

DROP TABLE IF EXISTS `quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quests` (
  `id` bigint(15) NOT NULL,
  `title` varchar(300) NOT NULL,
  `description` varchar(500) NOT NULL,
  `image` varchar(100) NOT NULL,
  `creation_date` date NOT NULL,
  `views` int(11) NOT NULL DEFAULT 0,
  `rating` double NOT NULL DEFAULT 0,
  `author_id` bigint(15) DEFAULT NULL,
  `first_question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quests_1_idx` (`author_id`),
  CONSTRAINT `fk_quests_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quests`
--

LOCK TABLES `quests` WRITE;
/*!40000 ALTER TABLE `quests` DISABLE KEYS */;
INSERT INTO `quests` VALUES
(1,'Угадайте новогодний фильм по одному кадру','Новый год - один из самых любимых праздников миллионов людей по всей земле, ну а какой праздник обходится без доброго кино, которое можно смотреть всей семьей? Новогодние фильмы - это уже отдельный жанр, в котором есть свои признанные шедевры. Мы уверены, эти фильмы горячо вами любимы и хорошо знакомы.','mmfvf6oiokj5lpz6wcq51.jpg','2023-01-12',57,9998,NULL,1),
(2,'Логические задачи. Сможешь решить?','Разгадайте задачи, ответ на которые сможет найти только внимательный человек, мыслящий гибко и нестандартно. Может показаться, что ответа на них нет, но отгадка проще простого и лежит на поверхности.','sherlok_holms1.jpg','2023-01-27',15,2,NULL,8);
/*!40000 ALTER TABLE `quests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES
(1,'ADMIN'),
(3,'EDITOR'),
(4,'GUEST'),
(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `profile_image` varchar(100) NOT NULL DEFAULT 'avatar_no_image.jpg',
  `played_quests_count` smallint(5) unsigned NOT NULL DEFAULT 0,
  `role_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles_id_idx` (`role_id`),
  CONSTRAINT `fk_users_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'Nataly','3071d8a2e2291975f0032b95411c0ab9317b3e220cb09bbd0dc727aed25c4668','0.png',591,2),
(2,'Nicole','2ee2340430e9b4bbdff1558d32845f1d853ff9c7f63ed239f1108f799236fefb','23.png',71,3),
(3,'Nora','2f00dadb22ce6ea20da7611cf5cb10fc9d2c30037cf687bd5be4cbaba12c0034','14.png',395,3),
(4,'Norma','b7688c518550be920369c8586cd9e2f8076fac33fb4fbd7dd299f94ecca84a7e','26.png',254,2),
(5,'Nancy','5a61e88a9dd24506c09566c043f91a996f3e282bab024141b35689deb6042414','26.png',174,2),
(6,'Audrey','8ebf8d0297ec876c73ae20618b883ba44ab17fe4100de39a2dbfab61b3a74af0','27.png',389,2),
(7,'Olivia','4aedc694ac7ca0710332b89d96cb706e5282d0dffe489981a163778481ca20dc','25.png',182,1),
(8,'Pamela','655344f7d1c6345b28f92cddde07f264f5d0936141ba28a604acac924a5cc413','11.png',660,3),
(9,'Patricia','f509e50ddc2d1907c1b4805fd6de9c00674cfbb6ceab2a244c4006aadbbff973','7.png',780,3),
(10,'Paula','e899e590eb3bc20f5a9becefd8c99dfd4737e2e2c1ed2742001c5dd3945824b0','11.png',816,2),
(11,'Peggy','e1cc9f23e1e2a2df63e917b4d3157bdc5d77ca4c47daecbbf5428579ef2f6d1c','27.png',386,1),
(12,'Page','2de4132b97978c51968dac3753cc250943a2d8ce8cbc88af6b4d37f5db1d7fed','22.png',239,3),
(13,'Penny','d30bdddcbfbde959b14b93c362c5a3a1f174ca000708400f1f7d6f77b11a2121','19.png',163,3),
(14,'Polly','b732999b2659570d7b3ba583b603611bffed272a1c75bf70c079fcba2b7b5acf','2.png',864,3),
(15,'Priscilla','559a2772e3d0e917606e02dcb9074e68f3144a7dc27b8dd4950b1442c3af0b06','20.png',398,1),
(16,'Rebecca','9a4afc008f78212d503c8f50225d0351dbce74d393e7c1878291ec5bc10a433b','4.png',156,2),
(17,'Regina','47723060763eeb78a9bfd68e7791cb6d924c55d7dd5c55feec67ff549dd713d5','18.png',51,3),
(18,'Rachel','82f42cf811732612ea35d46f2822b1b2630b8f07e3df130c24afb59dd5a59e3f','15.png',830,2),
(19,'Rosemary','ae3f7343e3b0ff1d4b5f501e9f231af136488fa6e14b73bbe794f2de7b07651c','12.png',933,2),
(20,'Rose','bebbb61cffdab85b92c5ef75f73cfe98294440d74e9905bb15e913e76909a94c','8.png',411,1),
(21,'Ruth','720020e6c7ba252d8b8a6faa39b3404cabfb1e4e6a775be6b7d236b52c1f35dc','3.png',938,3),
(22,'Sabrina','065027a1218f5ecdd6a93516a59de13c463949a198a13fabe61e33e18353415d','23.png',586,3),
(23,'Sally','eebc34cf13b633b4a9c3e7b62aadc8e2bdb17f151b81c5230da82baca1b12da0','27.png',737,3),
(24,'Samantha','44db06ebf4346d36cb676b84437c4ed0249d64823d6ba8818828ce557456d422','18.png',872,2),
(25,'Sandra','f6369f0899716214049032a8a5619d0b971dda1371e8dda92868f8bc456d7eba','5.png',991,3),
(26,'Sara','398f96cd06be36bc3c40fa0d996b0999d02cc3161f0cb160a1996ab49e0b5e26','11.png',912,1),
(27,'Selena','dfd50df6bbaec7068780894d6a55a9065cc30193acc1d48533cf82f40f6f5ffc','9.png',733,2),
(28,'Sandy','ad98b061e754bbfed1646b494ded157c739ab43783c7bbc68a3aadefdb9f74f8','24.png',941,3),
(29,'Cecil','04e8aa7ba2210963478fa684fa329bc296ccf761584b89d8eee245fef1da8e05','26.png',937,2),
(30,'Scarlet','a52780fad449ec064978f57fe18bd14a9b46f5c5c9b4f7964fb2a560dbe4c1ba','22.png',319,2),
(31,'Sophia','8da93216214813cf3e6aab4c32b594e3863957ca3df60a76a5d3c4600b0827e4','29.png',432,1),
(32,'Stella','3c53e0b47b83f64f626bf3bebc680c2c0854e367572ce34b6569b1baacf34755','3.png',573,1),
(33,'Susan','100b00932818a6fbf14fed3fce0a006bc4841dc43eff538ea241375ae0402662','12.png',370,3),
(34,'Susanna','2e525ae756485f5464800fd1fc36de106b66305e9ef98d21c64b0a313526c4e0','21.png',864,1),
(35,'Teresa','9e0ce0df1da4ac2d9616ce2390a60ed9e8b60b2a5125cef9153cb84725b5f09d','17.png',88,3),
(36,'Tina','775e918490470584a85859f23bb9e3799ed46e915ff5f33c51183b00a1293490','0.png',829,1),
(37,'Tiffany','9a08c06e35e84c8a357545a2a196b3a5edeb49ab5b847dbdf3b11c19a0ffff3d','27.png',455,1),
(38,'Tracy','4ba6fbb855d77ed0f97c6ab3f78007bc56088df005b838fcb048c652c945c238','16.png',965,2),
(39,'Florance','f504c8218a7218bcfe1e45b4d8d889191fc3454b2f2f5cbe65350fcba2103117','12.png',998,3),
(40,'Heather','7ea26dea574ef554c868883eee4dba6e360ba729c8a584b0c27ebbdb5c0838cd','27.png',44,1),
(41,'Chloe','f8bf793238c9acdd275e99eaf5e266e569cec77d067ffd1361b5e9cb815a9bd0','3.png',361,2),
(42,'Charlotte','bf775e55bd553f9af3818baf162b6b7ac6666cc07bd9256cb721a98579d85af8','24.png',784,1),
(43,'Sheila','e884e69abee9f3005a9ad92b61b1fac5e2031bffa466614e4e6f1b163eb285d2','7.png',75,1),
(44,'Cheril','7fca396f5e77953161b3d686d7b685e3e56b960393ae9852d26f98eb3e2ba20c','1.png',969,2),
(45,'Sharon','59b1a0a6337adbf34f51dec5748ce431e152912fa98b4ac37c660ccc1a237f83','11.png',755,3),
(46,'Sherry','4860d576c180f320ba3e8cde43ab912917722b028b9852fb71d398ee04a3b688','19.png',771,2),
(47,'Shirley','0e4a0eaca7f77ed2456f731a431c362bcbf1584413a2dc6df4dc0fd3e04ebd71','6.png',783,2),
(48,'Abigayle','925fd35ed087197d7bf62b9b43d317ccbadf13d08a92575aa2b95d12a4ab77bb','29.png',412,3),
(49,'Evelyn','b1b6db467d5b9fa9f75778b34ac1577f23005db85fe5c9ac9a536d881f6137ff','6.png',330,3),
(50,'Edison','cdda8b27a2e1e6114e4638db2313237b3b8c891109a5482db0d161333fa3ee88','1.png',807,1),
(51,'Edith','41bfe7b71f1477549072e2b0d999e38e4958c7236d9ff46043c2af9d3279bab2','28.png',692,1),
(52,'Avery','02aa4c37373455f04e8c6987c905ad3eb4af6c5eb4fbc4139d74e3c45c74ea40','15.png',102,1),
(53,'Eleanor','d0ce8cf1af7a63e26e3ab52ecd0744fb7fef0a40e45b7231c9b5739275694b9f','27.png',942,1),
(54,'Elizabeth','9c7ddb6bf86c654b9fec699908cf21c1c14bf03cc073d8889180eefe36188242','12.png',344,2),
(55,'Ella','6b7d676930966e2acd51d546b5c0ba51e75c82c40b9b92c9166529e2a9a219d6','7.png',316,3),
(56,'Emily','f8f69fd8aeb7037947a5d648486d565d8d8364e1057d3014a0b74edc35b6a66e','20.png',217,3),
(57,'Emma','c85faff4834e07d60d18a25dde59471da5d3e7e606796e72753c0b176b164a56','10.png',179,2),
(58,'Ester','b35bf83f9ccad32b869425780f8d6b53c48606c248cd514cfef383ad6911e825','9.png',594,1),
(60,'Login1','9dcefba6204adb4fefcdb5347d8baa2b7061dd9a7a2da981f6c307837668bbc0','16.png',24,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-27 12:39:33
