INSERT INTO categories (id, name)
VALUES (1, 'Fruits and Vegetables'),
       (2, 'Dairy Products'),
       (3, 'Bakery'),
       (4, 'Meat and Fish'),
       (5, 'Drinks');
INSERT INTO products (id, name, price, description, category_id)
VALUES (1, 'Apples Gala, 1 kg', 2.49, 'Fresh Gala apples, crisp and sweet, grown in Germany.', 1),
       (2, 'Bananas, 1 kg', 1.89, 'Ripe bananas from Ecuador, perfect for breakfast and smoothies.', 1),
       (3, 'Whole Milk 3.2%, 1 L', 1.29, 'Pasteurized whole milk from local farms.', 2),
       (4, 'Natural Yogurt, 150 g', 0.99, 'Plain yogurt with live cultures, rich in probiotics.', 2),
       (5, 'Sliced White Bread, 400 g', 1.59, 'Soft wheat bread with a golden crust.', 3),
       (6, 'Butter Croissant, 60 g', 1.19, 'Freshly baked croissant made with real butter.', 3),
       (7, 'Chicken Breast Fillet, 1 kg', 6.99, 'Premium boneless chicken breast, perfect for frying or baking.', 4),
       (8, 'Salmon Fillet, 300 g', 8.49, 'Fresh Atlantic salmon fillet, rich in omega-3 fatty acids.', 4),
       (9, 'Still Mineral Water, 1.5 L', 0.89, 'Pure spring water from alpine sources, non-carbonated.', 5),
       (10, 'Orange Juice 100%, 1 L', 2.29, 'Natural orange juice, not from concentrate, no added sugar.', 5);
