function [distr, max_dist, location] = positioning (loc, base_grid, ap1, ap2, ap3)
%POSITIONING random select the 100 groups data, do statistic 
%
% Calling sequence:
% [distr, max_dist, location] = positioning (loc, base, ap1, ap2, ap3)
%
% Define variables:
% loc -- the grid location
% base_grid -- base grid wifi info
% ap1 -- the ap1 data 
% ap2 -- the ap2 data
% ap3 -- the ap3 data
%
% Record of revisions:
% Date Pragrammer Description of change
% ======== ========== ================
% 11/27/2014 linjiang li Original code
%

rand_num = [];
rand_num(:, 1) = randi([1, size(ap1,1)], 100, 1);
rand_num(:, 2) = randi([1, size(ap2,1)], 100, 1);
rand_num(:, 3) = randi([1, size(ap3,1)], 100, 1);

base_grid = sortrows(base_grid);
hit_count = 0;

max_dist = 0;
location = [];

[~, loc_idx] = ismember(loc, base_grid(:, 1:2), 'rows');
if loc_idx==0
    error('invalid data', 'canot found the loc index');
    return;
end

for ii=1:100
    
    idx_ap1 = rand_num(ii, 1);
    idx_ap2 = rand_num(ii, 2);
    idx_ap3 = rand_num(ii, 3);
    
    dist = (ap1(idx_ap1, 1)-base_grid(:, 3)).^2 + (ap2(idx_ap2, 1)-base_grid(:, 4)).^2 + (ap3(idx_ap3, 1)- base_grid(:, 5)).^2;
    min_dist = min(dist);
    
    idx = find(dist==min_dist, 1, 'first');
    
    if base_grid(loc_idx, 3:5)==base_grid(idx, 3:5)
        idx = loc_idx;
    end
    
    if loc_idx==idx
        hit_count = hit_count + 1;
    end
    
    location(ii, :) = base_grid(idx, 1:2);
    
    if loc_idx~=idx
        err_dist = sum((location(ii, :)-loc).^2);
        
        if err_dist>max_dist
            max_dist = err_dist;
        end
    end
end

distr = hit_count;
max_dist = sqrt(max_dist);


        