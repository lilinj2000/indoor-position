function [ap1, ap2, ap3] = groupAPData (loc, ap_data)
%GROUPAPDATA according the loc, group the ap data, output each ap info
%
% Calling sequence:
% [ap1, ap2, ap3] = groupAPData(loc, ap_data)
%
% Define variables:
% loc -- the grid location
% ap data -- each grid the real collected wifi info
% ap1 -- the ap1 data 
% ap2 -- the ap2 data
% ap3 -- the ap3 data
%
% Record of revisions:
% Date Pragrammer Description of change
% ======== ========== ================
% 11/27/2014 linjiang li Original code
%

% sort the ap_data

ap_data = sortrows(ap_data);

loc_num = loc(1, 1)*10 + loc(1, 2);
ap_loc_num = ap_data(:, 1)*10 + ap_data(:, 2);

idx_first = find(ap_loc_num==loc_num, 1, 'first');
idx_last = find(ap_loc_num==loc_num, 1, 'last');

ap1 = [];
ap2 = [];
ap3 = [];

for ii=idx_first:idx_last
    
    switch ap_data(ii, 3)
        case 41
            ap1 = [ap1; ap_data(ii,4)];
        case 42
            ap2 = [ap2; ap_data(ii,4)];
        case 43
            ap3 = [ap3; ap_data(ii,4)];
    end
end

