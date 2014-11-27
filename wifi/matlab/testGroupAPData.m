%testGroupAPData Unit test

function test_suite = testGroupAPData
initTestSuite;

function ap_data = setup
ap_data = [
     3     2    43   -70;
     1     2    43   -65;
     3     2    42   -61;
     1     2    41   -69;
     3     2    41   -75;
     3     2    42   -63;
     3     2    43   -68;
     3     2    43   -69;
     1     2    42   -63;
     3     2    41   -71
     ];

% function teardown(fh)
% delete(fh);

function testLoc12(ap_data)

loc = [1, 2];
 
[ap1, ap2, ap3] = groupAPData(loc, ap_data);

exp_ap1 = [-69];
exp_ap2 = [-63];
exp_ap3 = [-65];

assertEqual(ap1, exp_ap1, 'ap1 not match');
assertEqual(ap2, exp_ap2, 'ap2 not match');
assertEqual(ap3, exp_ap3, 'ap3 not match');


function testLoc32(ap_data)

loc = [3, 2];
 
[ap1, ap2, ap3] = groupAPData(loc, ap_data);

exp_ap1 = [-75; -71];
exp_ap2 = [-63; -61];
exp_ap3 = [-70; -69; -68];

assertEqual(ap1, exp_ap1, 'ap1 not match');
assertEqual(ap2, exp_ap2, 'ap2 not match');
assertEqual(ap3, exp_ap3, 'ap3 not match');

