% Script file: wifiDataProcess.m 
%
% Purpose:
% Base on the wifi data, and simulator positionging,
% output the positioning result
%
%
% Record of revisions:
% Date Pragrammer Description of change
% ======== ========== ================
% 11/27/2014 linjiang li Original code
%

clear;
close all;

load apData;
load gridData;

% sort the grid data
grid_data = sortrows(gridFile);

result = [];
all_location = [];

for jj=1:10
    for ii=1:size(grid_data, 1)
        base_loc = grid_data(ii, 1:2);

        % group the ap data
        [ap1, ap2, ap3] = groupAPData(base_loc, apFile);

        % do positioning
        [distr, max_dist, location] = positioning (base_loc, grid_data, ap1, ap2, ap3);

        result = [result; base_loc, distr, max_dist];

        all_location = [all_location; repmat(base_loc, size(location, 1), 1), location]; 
    end
end
    
figure;
cdfplot(result(:,3));
title('hit probability distr');

figure;
cdfplot(result(:,4));
title('max error distance distr');

figure;
hold on;
base_loc = [2, 3];
for ii=1:size(all_location, 1)
    if base_loc==all_location(ii, 1:2)
        x = [all_location(ii, 1); all_location(ii, 3)];
        y = [all_location(ii, 2); all_location(ii, 4)];
        plot(x, y);
    end
end
grid on;

