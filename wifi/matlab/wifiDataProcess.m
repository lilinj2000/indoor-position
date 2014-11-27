
function result = wifiDataProcess (grid_data, ap_data)
%WIFIDATAPROCESS process the wifi base data, evaluate the result
% Function WIFIDATAPROCESS analysis the gridData and the apData,
% this function will random positioning 100 times for each grid,
% then output each grid hit probability and the max error distance,
% the positioning info each time of each grid.
%
% Calling sequence:
% res = wifiDataProcess(grid_data, ap_data)
%
% Define variables:
% grid_data -- each grid the base wifi info
% ap data -- each grid the real collected wifi info
% result -- each grid hit probability, max error distance, 
%           each position
%
% Record of revisions:
% Date Pragrammer Description of change
% ======== ========== ================
% 11/27/2014 linjiang li Original code
%

% sort the grid data
grid_data = sortrows(grid_data);

for ii=1:size(grid_data, 1)
    base_sig = grid_data(ii, 3:5);
    base_loc = grid_data(ii, 1:2);
    
    % group the ap data
    [ap1, ap2, ap3] = groupData(base_loc, ap_data);
    
    
    % random select ap data
    rand_num = random('Normal',0, 1000, 100, 3);
    
end
    
result = [];

end