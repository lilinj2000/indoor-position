function levelPlot()
%LEVELPLOT Plot the level info
% 

load map_data_0216;
close all;

map_0_0 = false;
map_0_1 = false;
map_0_2 = false;
map_0_3 = false;

map_0_01 = false;
map_0_0_01 = false;
map_0_0_01_1 = false;

map_0_31 = false;
map_0_3_31 = false;

% start 0216 data plot
map_rds_0216 = true;
map_tv_0216 = true;

for ii=1:length(macs)
    mac = macs{ii};
    ssid = ssids{ii};

    figure;
    hold on;

    legend_titles = cell(0);
%     colors = jet(9);
%     colors = hsv(9);
    colors = hot(9);
%     colors = pink(9);

    index = 0;

    if map_0_0 && map_info_0_0.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_0(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-0';
    end

    if map_0_1 && map_info_0_1.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_1(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-1';
    end

    if map_0_2 && map_info_0_2.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_2(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-2';
    end

    if map_0_3 && map_info_0_3.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_3(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-3';
    end

    if map_0_01 && map_info_0_01.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_01(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-01';
    end

    if map_0_0_01 && map_info_0_0_01.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_0_01(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-0-01';
    end

    if map_0_0_01_1 && map_info_0_0_01_1.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_0_01_1(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-0-01-1';
    end

    if map_0_31 && map_info_0_31.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_31(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-31';
    end

    if map_0_3_31 && map_info_0_3_31.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_0_3_31(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = '0-3-31';
    end
    
    if map_rds_0216 && map_info_rds_0216.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_rds_0216(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = 'rds-0216';
    end
    
    if map_tv_0216 && map_info_tv_0216.isKey(mac)
        index = index + 1;
        h = cdfplot(map_info_tv_0216(mac));
        set(h, 'Color', colors(index, :));
        legend_titles{index, 1} = 'tv-0216';
    end


    % cdfplot(data);
    title(vertcat(cellstr(mac), cellstr(ssid)));
    legend(legend_titles);
end

end

