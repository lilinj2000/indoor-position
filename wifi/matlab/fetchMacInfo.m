function  fetchMacInfo()
%FETCHMACINFO fetch the mac info from mac_info data file
%   From source data file, fetch the level data, and construct the map info 

file_name = 'mac_info';
[ssids, macs] = textread(file_name, '%s\t%s');

save('map_data_0226', '-append', 'macs', 'ssids');
% save('map_data_0216', 'map_info_rds_0216_1', 'mac_rds_0216_1', 'level_rds_0216_1');

end

