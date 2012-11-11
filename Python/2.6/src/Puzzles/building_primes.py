import sys

g_intLargestPrime = 2

#------------------------------------------------------------------------------
def primes(n):
##primes(n) --> primes
##
##Return list of primes from 2 up to but not including n.  Uses Sieve of Erasth.

    if n < 2:
        return []

    nums = range(2,int(n))
    p = []

    while nums:
        new_prime = nums[0]
        p.append(new_prime)

        for i in nums[1:]:
            if i % new_prime == 0:
                nums.remove(i)
        nums.remove(nums[0])

    return p

def power_mod(a, b, n):
##power_mod(a,b,n) --> int
##
##Return (a ** b) % n

    if b < 0:
        return 0
    elif b == 0:
        return 1
    elif b % 2 == 0:
        return power_mod(a*a, b/2, n) % n
    else:
        return (a * power_mod(a,b-1,n)) % n
    
def rabin_miller(n, tries = 2):
##rabin_miller(n, tries) --> Bool
##
##Return True if n passes Rabin-Miller strong pseudo-prime test on the
##given number of tries, which indicates that n has < 4**(-tries) chance of being composite; return False otherwise.
##
##http://mathworld.wolfram.com/Rabin-MillerStrongPseudoprimeTest.html

    if n == 2:
        return True
    
    if n % 2 == 0 or n < 2:
        return False
    
    p = primes(tries**2)

    # necessary because of the test below
    if n in p:
        return True
    
    s = n - 1
    r = 0
    while s % 2 == 0:
        r = r+1
        s = s/2
    for i in range(tries):
        a = p[i]
        
        if power_mod(a,s,n) == 1:
            continue
        else:
            for j in range(0,r):
                if power_mod(a,(2**j)*s, n) == n - 1:
                    break
            else:
                return False
            continue
    return True

#------------------------------------------------------------------------------
def AddDigit (pstrPrime,pintLevel):
    # Add a digit to the right of the given prime and see if the new number is
    # prime.  If so, repeat the process.

    global g_intLargestPrime

    # Use this to control how many recursions to do (for testing)
#    if (pintLevel == 8):
#        return 0

    for intNewDigit in range (0, 10):
        strNewPrime = pstrPrime + str(intNewDigit)
        intNewPrime = int(strNewPrime)

        sys.stdout.write("Testing " + strNewPrime + "... ")

        if (rabin_miller(intNewPrime) == True):
            sys.stdout.write("Prime!")
            print ""

            # If this new prime is the largest, store it
            if (intNewPrime > g_intLargestPrime):
                g_intLargestPrime = intNewPrime

            # Add a digit to the new prime
            AddDigit(strNewPrime,pintLevel + 1)

        else:
            sys.stdout.write("not prime")
            print ""
        
    return strNewPrime

#------------------------------------------------------------------------------
# Main program
#------------------------------------------------------------------------------

print "Building primes to the right:"
print ""

# Just start with a single digit prime and go from there
for intDigit in (2, 3, 5, 7):
    strPrime = str(intDigit)
    AddDigit(strPrime,1)

print "The largest prime is " + str(g_intLargestPrime)
