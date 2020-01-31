# MyProject contains payment assesment module.

# It has endpoint "/getNetPay" to give information of payment after applying valid offers.

#The above endpoint consumes DTO PaymentDTO with fields as following.
 1) payment = It is not null field indicating the amount on which offer has to be applied.
 2) userStatus = A not null field to check the user regarding appropriate offers to be availed("isEmp"=If the user is retail employer, "isAffl"= if the user is affiliate, "isCstmrYr2"= if the user is associated with retail shop from past 2 years).
 3) isGrocery = A field to check if the bought item are groceries or not.
 
#The above endpoint give response in form of PaymentResponse with info as following.
 1) netPay = Total amount to be paid be user after availed offers.
 2) errorMessage = error message if something wents wrnog in the api.
 3) errorCode = error code for associated errors.
 
#Run the app after making a maven build using command as follows.
 1) mvn clean install.
 2) select AppStarter file and run it as Spring boot application.
 3) hit the port 8070 on localhost through any rest client followed by endpoint url "/getNetPay" keeping method type to be "POST" and content type to be "application/json".
 
