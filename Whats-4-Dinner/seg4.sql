-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 06, 2022 at 02:29 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `seg4`
--

-- --------------------------------------------------------

--
-- Table structure for table `favorited`
--

CREATE TABLE `favorited` (
  `user_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `favorited`
--

INSERT INTO `favorited` (`user_id`, `recipe_id`, `id`) VALUES
(9, 2, 18),
(3, 3, 38);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `ingredient_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`ingredient_id`, `name`) VALUES
(1, 'Egg(s)'),
(2, 'Tomato Sauce'),
(3, 'Chickpeas'),
(4, 'Spinach'),
(5, 'Grape Tomatoes'),
(6, 'Yellow Curry Paste'),
(7, 'Cream'),
(8, 'Ginger'),
(9, 'Shallot'),
(10, 'Olive Oil'),
(11, 'Salt'),
(12, 'Pepper'),
(13, 'Garlic'),
(15, 'Green Beans'),
(16, 'Almonds'),
(17, 'Butter'),
(18, 'Rice Linguini Noodles'),
(19, 'Raw Shrimp, Peeled and Deveined'),
(20, 'Sweet Chili Sauce'),
(21, 'Snow Peas'),
(25, 'Extra-firm Tofu'),
(26, 'Canola Oil'),
(27, 'Soy Sauce'),
(31, 'Chili Paste'),
(33, 'Toasted Sesame Seeds'),
(34, 'Sesame Oil'),
(35, 'Ripe Avocado'),
(38, 'Whole Grain or Wheat Bread'),
(40, 'Extra-virgin olive oil or unsalted butter, softened'),
(42, 'Crushed Red Pepper Flakes, Optional'),
(44, 'Large Carrots'),
(45, 'Celery'),
(47, 'All-Purpose Flour'),
(48, 'Boneless Skinless Chicken Breasts'),
(49, 'Fresh Thyme'),
(50, 'Bay Leaf'),
(51, 'Baby Potatoes'),
(52, 'Chicken Broth'),
(53, 'Fresh Parsley'),
(54, 'Hoisin Sauce'),
(55, 'Rice Wine Vinegar'),
(56, 'Sriracha, Optional'),
(57, 'Medium Onion'),
(58, 'Ground Chicken'),
(59, 'Canned Water Chestnuts'),
(60, 'Green Onions'),
(61, 'Large Leaf Lettuce'),
(62, 'Cooked White Rice, Optional');

-- --------------------------------------------------------

--
-- Table structure for table `pantry`
--

CREATE TABLE `pantry` (
  `name` varchar(100) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pantry`
--

INSERT INTO `pantry` (`name`, `ingredient_id`, `user_id`) VALUES
('Garlic', 13, 1),
('Green Beans', 15, 1),
('Butter', 17, 1),
('Green Beans', 15, 9),
('Almonds', 16, 9),
('Snow Peas', 21, 9),
('Salt', 11, 9),
('Rice Linguini Noodles', 18, 9),
('Garlic', 13, 9),
('Butter', 17, 9),
('Cooked White Rice, Optional', 62, 9),
('Large Leaf Lettuce', 61, 9),
('Green Onions', 60, 9),
('Ground Chicken', 58, 9),
('Medium Onion', 57, 9),
('Garlic', 13, 3),
('Green Beans', 15, 3),
('Almonds', 16, 3),
('Butter', 17, 3),
('Salt', 11, 3),
('Pepper', 12, 3),
('Snow Peas', 21, 3),
('Raw Shrimp, Peeled and Deveined', 19, 3),
('Salt', 11, 17),
('Pepper', 12, 17),
('Rice Linguini Noodles', 18, 17),
('Raw Shrimp, Peeled and Deveined', 19, 17);

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE `recipes` (
  `recipe_id` int(11) NOT NULL,
  `directions` text NOT NULL,
  `pic` varchar(10000) NOT NULL,
  `name` varchar(100) NOT NULL,
  `servings` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recipes`
--

INSERT INTO `recipes` (`recipe_id`, `directions`, `pic`, `name`, `servings`) VALUES
(1, '<ol>\r\n	<li>Prepare ingredients:\r\n		<ul>\r\n			<li>Peel and chop shallot.</li>\r\n			<li>Peel and mince ginger</li>\r\n			<li>Halve the grape tomatoes</li>\r\n			<li>Rinse and drain chickpeas</li>\r\n		</ul>\r\n	</li>\r\n	<li>Make the Sauce:\r\n		<ul>\r\n			<li>Heat drizzle of olive oil on medium-high until hot</li>\r\n			<li>Add chopped shallot and minced ginger, stirring occasionally, 1-2 minutes</li>\r\n			<li>Add the yellow curry paste, stir to combine, about 30 seconds</li>\r\n			<li>Add the drained chickpeas, season with salt & and pepper, stir to combine. 1-2 minutes.</li>\r\n		</ul>\r\n	<li>Finish the sauce: \r\n		<ul>\r\n			<li>Add the tomato sauce, tomatoes, and 1/2 cup of water. Stirring occasionally until liquid is reduced, cook 2-3 minutes.</li>\r\n			<li>Add the spinach and half of the cream (~4Tbsp). Stir until combined and the spinach is starting to wilt, about 30 seconds.</li>\r\n		</ul>\r\n	<li>Cook the eggs & serve:\r\n		<ul>\r\n			<li>Using a spoon, create 2 shallow wells in the center of your sauce. Crack an egg into each well;</li>\r\n			<li>Season with salt and pepper. Cover the pan loosely with a lid or foil. Cook, until the egg whites are set, 4-5 minutes.</li>\r\n			<li>Serve the shakshuka drizzled with the remaining half of the cream</li>\r\n		</ul>\r\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Chickpea Curry Shakshuka\">\n<input type=\"image\" src=\"views/assets/shakshuka_smol.png\" alt=\"Chickpea Curry Shakshuka\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Chickpea Curry Shakshuka', 2),
(2, '<ol>\r\n	<li>Prepare:\r\n		<ul>\r\n			<li>Chop garlic</li>\r\n			<li>Slice almonds</li>\r\n			<li>Clean and trim green beans</li>\r\n		</ul>\r\n	</li>\r\n	<li>Cook:\r\n		<ul>\r\n			<li>Melt butter over moderate heat, add beans and chopped garlic.</li>\r\n			<li>Reduce heat and cook for 7-10 minutes on low, stirring occasionally.</li>\r\n			<li>In a separate non-stick pan, <em>carefully</em> toast the almonds to a light golden color over medium heat.</li>\r\n			<li>Once the beans are fully cooked, add the almonds and stir thoroughly.</li>\r\n                        <li>Season with salt and pepper to taste, and serve.</li>\r\n		</ul>\r\n	</li>\r\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Green Beans Almondine\">\n<input type=\"image\" src=\"views/assets/GB_Almondine_Smol.jpeg\" alt=\"Green Beans Almondine\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Green Beans Almondine', 1),
(3, '<ol>\n    <li>Cook:\n	<ul>\n	    <li>Bring 3-4 quarts of water to a boil and remove from heat. </li>\n	    <li>Place rice linguine noodles in a large metal or glass bow and cover with hot water for 25 minutes stirring with a fork frequently to break up any noodle clumps. After about 15 minutes water should be cool enough to pull out and separate any clumped noodles. </li>\n	    <li>Rinse shrimp and place on a paper towel-lined plate. Pat dry and season with salt and pepper on both sides. Add shrimp and 1 tablespoon of sweet chili sauce to a container with a lid. refrigerate for at least 15 minutes.</li>\n	    <li>Drain and rinse noodles and return to the bowl. Gather your ingredients. (noodles, shrimp, remaining 1/4 cup sweet chili sauce, and snow peas) in bowls and place on a sheet tray next to the stove.</li>\n<li> Heat a large skillet or wok over high heat. Add 1 tablespoon of oil and swirl in pan to coat. When you see the first wisp of smoke, add shrimp and cook until they begin to turn pink. 1 minute on each side. The sugars in the sweet chili sauce should caramelize just a little bit and create tasty bits in the pan. </li>\n<li> Make space in the middle of the pan, add the remaining 1 tablespoon oil and sliced snow peas and cook for 2 minutes. Add prepared noodles and 1/4 cup of sweet chili sauce. Continue to cook until noodles are coated and heated through, about 4 to 6 minutes, Serve immediately.</li>\n		</ul>\n	</li>\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Shrimp and Snow Pea Stir Fry\">\n<input type=\"image\" src=\"views/assets/shrimpsnowpeasstirfry.jpg\" alt=\"Shrimp and Snow Pea Stir Fry\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Shrimp and Snow Pea Stir Fry', 4),
(4, '<ol>\n	<li>Prepare:\n		<ul>\n			<li>Drain the tofu</li>\n			<li>Wrap each block in a double layer of paper towels and pat dry, pressing down on the tofu lightly to squeeze out excess moisture</li>\n			<li>Cut the tofu into 3/4-inch cubes</li>\n		</ul>\n	<li>Cook:</li>\n		<ul>\n			<li>In a large nonstick skillet or wok, heat the canola oil over medium-high heat</li>\n			<li>Once the oil is hot but not smoking, add the tofu (be careful, as the oil will splatter) and drizzle with 1 tablespoon of soy sauce</li>\n			<li>Sauté, stirring every minute or so until the moisture has cooked off and the tofu starts to brown, about 8 to 10 minutes</li>\n			<li>Add the garlic, roughly two-thirds of the green onion, ginger, chili paste, and the remaining 2 tablespoons of soy sauce. Stir about 1 minute. </li>\n			<li>Add spinach, stirring as you go so that it wilts, until all of the spinach is added</li>\n			<li>Stir in the sesame seeds and the sesame oil, and remove from the heat</li>\n			<li>Sprinkle the reserved green onions over the top. Serve hot, with brown rice, noodles, along with a few dashes of additional soy sauce and chili paste or flakes to taste.</li>\n		</ul>\n	</li>\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Tofu Stir Fry\"><input type=\"image\" src=\"views/assets/tofudish.png\" alt=\"Tofu Stir fry\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Tofu Stir Fry', 4),
(5, '<ul style=\"font-family:arial;\">\n	<li>Mash the avocado with a fork in a shallow bowl until chunky. Season with fine salt and black pepper to taste</li>\n	<li>Toast the bread until browned and crisp</li>\n	<li>Lightly rub 1 side of each slice of bread with the garlic until fragrant; discard the garlic</li>\n	<li>Lightly brush the toasts with oil, and season with fine salt and pepper</li>\n	<li>Divide the mashed avocado evenly among the toasts, and top with more flaky sea salt, more black pepper and red pepper flakes to taste</li>\n</ul>', '<input type=\"hidden\" name=\"action\" value=\"Avocado Toasts\"><img src=\"views/assets/avadish.png\" alt=\"Avocado Toasts\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Avocado Toasts', 4),
(6, '<ol>\n	<li>Prepare:\n		<ul>\n			<li>Peel and slice carrots into coins</li>\n			<li>Chop celery</li>\n			<li>Mince garlic gloves</li>\n			<li>Quarter baby potatos</li>\n			<li>Chop parasley</li>\n		</ul>\n	</li>\n	<li>Cook:\n		<ul>\n			<li>In a large pot over medium heat, melt butter.</li>\n			<li>Add carrots and celery and seaon with salt and pepper<?li>\n			<li>Cook, stirring often, until vegetables are tender, about 5 minutes</li>\n			<li>Add garlic and cook untill fragrant, about 30 seconds</li>\n			<li>Add flour and stir utill vegetables are coated, then add chicken, thyme, bay leat, potatoes, and broth</li>\n			<li>Season with salt and pepper to taste</li> \n			<li>Bring mixture to a simmer and cook until the chicken is no longer pink and potatoes are tender, 15 minutes.</li>\n               <li>Remove from heat and transfer chicken to a medium bowl</li>\n               <li>Using two forks, shred chicken into small pieces and return to pot.</li>\n			<li> Garnish with parsley before serving.</li>\n		</ul>\n	</li>\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Chicken Stew\"><img src=\"views/assets/chickenstew.png\" alt=\"Chicken Stew\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Chicken Stew', 4),
(7, '<ol>\n	<li>Prepare:\n		<ul>\n			<li>Dice onion</li>\n			<li>Grate Ginger</li>\n			<li>Drain and Slice water chestnutes</li>\n			<li>Thinly slice green onions</li>\n			<li>Wash and separate lettuce leaves</li>\n			<li>In a small bowl, whisk together hoisin sauce, soy sauce, rice wine vinegar, Sriracha, and sesame oil.</li>\n		</ul>\n	</li>\n	<li>Cook:\n		<ul>\n			<li>In a large skillet over medium-high heat, heat olive oil</li>\n			<li>Add onions and cook until soft, 5 minutes</li>\n			<li>Stir in garlic and ginger and cook until fragrant, about another minute</li>\n			<li>Add ground chicken and cook until opaque and cooked through, breaking up the meat with a wooden spoon.</li>\n			<li>Pour in the liquids and cook 1 to 2 minutes more, until the sauce reduces slightly</li>\n			<li>Turn off the heat and stir in chestnuts and green onions. Season with salt and pepper.</li>\n			<li>Spoon rice, if using, and a large scoop (about 1/4 cup) of chicken mixture into the center of each lettuce leaf. Serve immediately.</li>\n          </ul>\n	</li>\n</ol>', '<input type=\"hidden\" name=\"action\" value=\"Copycat Chicken Lettuce Wraps\"><img src=\"views/assets/lettucewrap.png\" alt=\"Copycat Chicken Lettuce Wraps\" title=\"Add/Remove from Favorites\" style=\"border-radius: 5px;\"/>\n</form>', 'Copycat Chicken Lettuce Wraps', 4);

-- --------------------------------------------------------

--
-- Table structure for table `recipe_ingredient`
--

CREATE TABLE `recipe_ingredient` (
  `amount` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `measure` varchar(11) NOT NULL,
  `recipe_id` int(100) NOT NULL,
  `recipe_ingredient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recipe_ingredient`
--

INSERT INTO `recipe_ingredient` (`amount`, `ingredient_id`, `measure`, `recipe_id`, `recipe_ingredient_id`) VALUES
(2, 1, 'each', 1, 1),
(8, 2, 'oz', 1, 2),
(16, 3, 'oz', 1, 3),
(3, 4, 'oz', 1, 4),
(4, 5, 'oz', 1, 5),
(1, 6, 'tbsp', 1, 6),
(2, 7, 'oz', 1, 7),
(2, 8, 'tsp', 1, 8),
(1, 9, 'each', 1, 9),
(0, 10, '', 1, 10),
(0, 11, '', 1, 11),
(0, 12, '', 1, 12),
(3, 17, 'Tbsp', 2, 33),
(3, 13, 'cloves', 2, 34),
(7, 15, 'oz', 2, 35),
(3, 16, 'oz', 2, 36),
(8, 18, 'oz', 3, 42),
(8, 19, 'oz', 3, 43),
(3, 20, 'oz', 3, 44),
(8, 21, 'oz', 3, 45),
(0, 11, 'to taste', 3, 46),
(0, 12, 'to taste', 3, 47),
(2, 24, 'tbsp', 3, 48),
(28, 25, 'oz', 4, 76),
(1, 26, 'tbsp', 4, 77),
(3, 27, 'tbsp', 4, 78),
(3, 13, 'each', 4, 79),
(1, 4, 'small bunch', 4, 80),
(1, 8, 'tbsp', 4, 81),
(2, 31, 'tsp', 4, 82),
(10, 32, 'oz', 4, 83),
(2, 33, 'tbsp', 4, 84),
(2, 34, 'tsp', 4, 85),
(8, 35, 'oz', 5, 86),
(0, 11, 'to taste', 5, 87),
(0, 12, 'to taste', 5, 88),
(4, 38, 'slices', 5, 89),
(1, 39, 'clove', 5, 90),
(2, 40, 'tbsp', 5, 91),
(0, 11, 'to taste', 5, 92),
(0, 12, 'to taste', 5, 93),
(2, 17, 'tbsp', 6, 94),
(2, 44, 'each', 6, 95),
(1, 45, 'stalk', 6, 96),
(0, 11, 'to taste', 6, 97),
(0, 12, 'to taste', 6, 98),
(3, 13, 'cloves', 6, 99),
(1, 47, 'tbsp', 6, 100),
(24, 48, 'oz', 6, 101),
(3, 49, 'sprigs', 6, 102),
(1, 50, 'each', 6, 103),
(12, 51, 'oz', 6, 104),
(3, 52, 'cups', 6, 105),
(0, 53, '', 6, 106),
(3, 54, 'tbsp', 7, 107),
(2, 27, 'tbsp', 7, 108),
(2, 55, 'tbsp', 7, 109),
(1, 56, 'tbsp', 7, 110),
(1, 34, 'tsp', 7, 111),
(1, 63, 'tbsp', 7, 112),
(1, 57, 'each', 7, 113),
(2, 12, 'cloves', 7, 114),
(1, 8, 'tbsp', 7, 115),
(1, 58, 'lb', 7, 116),
(1, 59, 'cup', 7, 117),
(2, 60, 'each', 7, 118),
(0, 11, 'to taste', 7, 119),
(0, 12, 'to taste', 7, 120),
(1, 61, 'package', 7, 121),
(2, 62, 'cups', 7, 122);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `password_hash` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `password`, `email_address`, `password_hash`) VALUES
(3, 'Kevin', 'test', 'kevlarski007@gmail.com', '$2y$10$4Q56moXQaDM0zuLikIY45e4LtS3vFKP2Ojyx1ogCcf7EX5rslPmM6'),
(9, 'test', 'test', 'test@gmail.com', '$2y$10$pa/Ncion.PTYX3OHET2EbuaiYQl0RnVWJAMNcApNL6xRkIFjSd10.'),
(17, 'Kevin McLaughlin', 'test', 'kevinmclaughlin007@gmail.com', '$2y$10$yK5my01qd.pEUD/uAKN0VOpfG0A72HFukcF9kijOryCDDY9qwy/jG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favorited`
--
ALTER TABLE `favorited`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recipe_id` (`recipe_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`ingredient_id`);

--
-- Indexes for table `pantry`
--
ALTER TABLE `pantry`
  ADD KEY `ingredient_id` (`ingredient_id`,`user_id`);

--
-- Indexes for table `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`recipe_id`);

--
-- Indexes for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD PRIMARY KEY (`recipe_ingredient_id`),
  ADD KEY `ingredient_id` (`ingredient_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_address` (`email_address`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `favorited`
--
ALTER TABLE `favorited`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `ingredient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
  MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `recipe_ingredient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorited`
--
ALTER TABLE `favorited`
  ADD CONSTRAINT `favorited_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favorited_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pantry`
--
ALTER TABLE `pantry`
  ADD CONSTRAINT `pantry_ibfk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`ingredient_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
