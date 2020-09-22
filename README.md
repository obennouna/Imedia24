# IMedia24 
  
This repository contains the source code of the Application for **IMedia24** in regards of the interview.
The goal of this application is to provide a simple eCommerce application with some categories/products to browse and to add in a Basket  
  
![Home Page](https://i.imgur.com/ZhtNUVV.png "Home Page")
![Categories](https://i.imgur.com/ULan9mR.png "Categories")
![Drawer Menu](https://i.imgur.com/AnZef6N.png "Drawer Menu")
![Product List](https://i.imgur.com/2bLcdJC.png "Product List")
![Product Detail](https://i.imgur.com/c14vM9N.png "Product Detail")
![Cart](https://i.imgur.com/K5qj8Xb.png "Cart")

# Technical Environment
 - Android Studio 4.0.1
 - MinSDKVersion 16 (Android 4.1)
 - CompileSDKVersion 30
 - BuildToolsVersion 30.0.2  
 - KotlinVersion 1.4.10  

# To note
- The small number displayed near the category icon in the list of Categories screen is the number of subcategories and not number of products (might be confusing)
- No pull to refresh based on Amazon design
- The global Architecture is based on MVVM using Coroutines, Room etc...
- Excuse the design :)

# Some more stuff to improve for futur versions
- Sorry but no Unit Tests, nor Functional Tests
- Add some CI/CD either using Jenkins or Gitlab CI/Github CL to automate the deploy
- For every Image, implement a carousel to display all picture and not only the first one
- Add search feature based on the name
- Improve caching by adding an expiration time (at the moment not needed as this is unlikely to be dynamic)
- Remove both ViewModels to keep only one
- Give the possibility in the cart to reduce the number of element instead of a full delete
- Optimize the use of Styles in layout
- Add Swipe to delete in Cart Screen
- Simplify Activity/Fragment hierarchy (use one activity with multiple fragment for example)
- Add number of element in the icon in cart as a badge
- Add translations for at least English and German
- Add some new screen for tablet users
