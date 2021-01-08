package com.github.smallmenu.crypto;

import org.junit.Test;

import static com.github.smallmenu.Fun.toStr;

public class HashidsTest {

    @Test
    public void testHashids() {
        Hashids hashids1 = new Hashids("this is my salt");
        String hash1 = hashids1.encode(12345L);
        long[] numbers = hashids1.decode("NkK9");

        System.out.println(hash1);
        System.out.println(toStr(numbers));

        Hashids hashids2 = new Hashids("this is my salt", 8);
        String hash2 = hashids2.encode(1L);
        System.out.println(hash2);
    }
}
