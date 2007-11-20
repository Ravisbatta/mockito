/*
 * Copyright (c) 2001-2007 OFFIS, Tammo Freese.
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.usage.verification;

import static org.junit.Assert.*;
import static org.mockito.CrazyMatchers.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import java.util.*;

import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;
import org.mockito.exceptions.VerificationAssertionError;
import org.mockito.usage.IMethods;

@SuppressWarnings("unchecked")  
public class VerificationInOrderTest {
    
    private LinkedList list;
    private HashMap map;
    private HashSet set;

    @Before
    public void setUp() {
        list = mock(LinkedList.class);
        map = mock(HashMap.class);
        set = mock(HashSet.class);

        list.add("one");
        map.put("two", "two");
        list.add("three and four");
        list.add("three and four");
        map.put("five", "five");
        set.add("six");
    }

    @Ignore
    @Test
    public void shouldVerifyInOrder() {
        Mockito.verifyInOrder(list).add("one");
        Mockito.verifyInOrder(map).put("one", "two");
        Mockito.verifyInOrder(list).add("three and four");
        Mockito.verifyInOrder(map).put("five", "five");
        Mockito.verifyInOrder(set).add("six");
    } 

    @Ignore
    @Test
    public void shouldVerifyInOrderWithExactNumberOfInvocations() {
        Mockito.verifyInOrder(list, 1).add("one");
        Mockito.verifyInOrder(map).put("one", "two");
        Mockito.verifyInOrder(list, 2).add("three and four");
        Mockito.verifyInOrder(map, 1).put("five", "five");
        Mockito.verifyInOrder(set, 1).add("six");
    }  
}