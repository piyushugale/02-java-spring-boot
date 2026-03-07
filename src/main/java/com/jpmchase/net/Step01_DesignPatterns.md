Here are the top 5 design patterns explained in simple, interview-ready statements:

Strategy Pattern: 
    This lets you swap business logic or algorithms at runtime. It’s perfect for handling different payment methods or regional rules without changing your main code.
Observer (Pub-Sub) Pattern: 
    This is the heart of event-driven apps. It allows one part of your system (like a database change) to automatically notify other parts (like a frontend UI or a notification service).
Circuit Breaker Pattern: 
    This is a cloud essential. It stops your app from repeatedly trying to call a failing service, which "fails fast" and prevents your entire system from crashing.
Factory Pattern: 
    This hides the "how" of creating complex objects. It’s great for creating different service types based on the environment, like choosing between a "Mock Service" for testing and a "Real Cloud Service" for production.
Singleton Pattern: 
    This ensures that only one instance of a class exists. You use this to manage shared resources that shouldn't be duplicated, such as database connection pools or global configuration settings.






FINAL keyword -
Variable	Value cannot be reassigned after initialization.
Method	    Cannot be overridden in subclasses.
Class	    Cannot be extended (no inheritance).


    
    public final class SecuritySystem {                                             // 1. FINAL CLASS: No one can create a subclass of SecuritySystem

        private final String serialNumber;                                          // 2. FINAL VARIABLE: The serial number cannot be changed once set
    
        public SecuritySystem(String serialNumber) {
            this.serialNumber = serialNumber;
        }
    
        public final void activate() {                                              // 3. FINAL METHOD: Subclasses (if this weren't a final class) or internal logic cannot override this core behavior.
            
            final String status = "ARMED";                                          // 4. LOCAL FINAL VARIABLE: Only used within this scope
            System.out.println("System " + serialNumber + " is now " + status);
            
            status = "DISARMED";                                                    // COMPILER ERROR: Cannot reassign final variable
        }
    
        public String getSerialNumber() {
            return serialNumber;
        }
    }

ATTEMPTING TO BREAK THE RULES:
        
        class AdvancedSecurity extends SecuritySystem                               // COMPILER ERROR: Cannot inherit from final 'SecuritySystem'
        {
            // logic
        }

