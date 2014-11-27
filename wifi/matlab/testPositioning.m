%testPositioning Unit test

function test_suite = testPositioning
initTestSuite;

function data = setup
 
 data.base_grid = [
     1     2   -68   -62   -68;
     1     3   -71   -64   -70;
     1     4   -68   -62   -68;
     1     5   -69   -64   -70;
     1     6   -70   -64   -70;
     1     8   -69   -64   -68;
     2     1   -69   -64   -68;
     2     2   -69   -62   -68;
     2     3   -68   -62   -68;
     2     4   -69   -64   -68;
     3     2   -68   -62   -68;
     3     3   -70   -64   -70;
     ];
 
 data.ap1 = [
   -64;
   -68;
   -67;
   -67;
   -67;
   -67;
   -67;
   -66;
     ];
 
 data.ap2 = [
   -61;
   -60;
   -60;
   -64;
   -63;
   -63;
   -63;
   -63;
   -63;
   -62;
   -62;
     ];
 
 data.ap3 = [
   -64;
   -64;
   -68;
   -67;
   -67;
   -67;
     ];

% function teardown(fh)
% delete(fh);

function testLoc12(data)

loc = [1, 2];
 
[distr, max_dist, location] = positioning(loc, data.base_grid, data.ap1, data.ap2, data.ap3);

disp(['distr is ', num2str(distr)]);
disp(['max_ dist is ', num2str(max_dist)]);
assertEqual(size(location, 1), 100, 'location size not match');

function testLoc23(data)

loc = [2, 3];
 
[distr, max_dist, location] = positioning(loc, data.base_grid, data.ap1, data.ap2, data.ap3);

disp(['distr is ', num2str(distr)]);
disp(['max_ dist is ', num2str(max_dist)]);
assertEqual(size(location, 1), 100, 'location size not match');

