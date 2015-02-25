
% fetchLevel();

% file_name = 'filter_0_0';
% [mac, level] = textread(file_name, '%s\t%d');
% 
% map_info = containers.Map();

% for ii=1:length(mac)
%     if map_info.isKey(mac{ii})
%         map_info(mac{ii}) = [map_info(mac{ii}); level(ii)];
%     else
%         map_info(mac{ii}) = level(ii);
%     end
% end

% cdfplot(map_info(mac{1}));

% map_info_0_0 = map_info;
% 
% save('map_data_0129', '-append', 'map_info_0_0');

% map_info_0_3_31 = map_info_0_3;
% 
% keys_0_31 = map_info_0_31.keys();
% 
% for ii = 1:length(keys_0_31)
%     if map_info_0_3_31.isKey(keys_0_31{ii})
%         map_info_0_3_31(keys_0_31{ii}) = [map_info_0_3_31(keys_0_31{ii}); map_info_0_31(keys_0_31{ii})];
%     else
%         map_info_0_3_31(keys_0_31{ii}) = map_info_0_31(keys_0_31{ii});
%     end
% end

% mergeMaps();

% mac = '28:2c:b2:5a:8c:be';
% 
% figure;
% hold on;
% 
% legend_titles = cell(0);
% colors = jet(10);
% %     data(:, ii) = map_info_0_0(macs{ii});
% h = cdfplot(map_info_0_0(mac));
% set(h, 'Color', colors(1, :));
% legend_titles{1, 1} = '0-0';
% 
% h = cdfplot(map_info_0_01(mac));
% set(h, 'Color', colors(2, :));
% legend_titles{2, 1} = '0-01';
% 
% h = cdfplot(map_info_0_0_01(mac));
% set(h, 'Color', colors(3, :));
% legend_titles{3, 1} = '0-0-01';
% 
% h = cdfplot(map_info_0_1(mac));
% set(h, 'Color', colors(4, :));
% legend_titles{4, 1} = '0-1';
% 
% h = cdfplot(map_info_0_0_01_1(mac));
% set(h, 'Color', colors(5, :));
% legend_titles{5, 1} = '0-0-01-1';
% 
% % cdfplot(data);
% title(mac);
% legend(legend_titles);
% title(vertcat(cellstr('0-0'), cellstr(mac)));

% vertcat({'radius vs cell type'}, subhead)

% figure;
% cdfplot(map_info_0_1(mac));
% 
% figure;
% cdfplot(map_info_0_2(mac));
% 
% figure;
% cdfplot(map_info_0_3(mac));

levelPlot();

% fetchMacInfo;

