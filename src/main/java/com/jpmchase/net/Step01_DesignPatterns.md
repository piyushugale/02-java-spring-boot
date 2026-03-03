


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

