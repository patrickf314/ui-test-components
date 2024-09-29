// Select an option from the data list of an input element which start with a specific prefix
// noinspection BadExpressionStatementJS
/**
 * Getter for the first option starting with the given prefix.
 * This function will wait up to the specified timeout, to check for changes in
 * the select element.
 *
 * @param {HTMLSelectElement} node the select element with the data list
 * @param {{timeout: number, parameter: {group: string, label: string}}} arg the argument
 * @returns {Promise<string>} the promise resolving to the full value of the matching option
 */
(node, {timeout, parameter}) => new Promise((resolve, reject) => {
    const {group, label} = parameter;

    let counter = timeout * 20;

    function checkOptions() {
        const ungrouped = [];
        const grouped = {};

        for (let i = 0; i < node.options.length; i++) {
            const option = node.options[i];
            const optionGroup = option.parentElement instanceof HTMLOptGroupElement ? option.parentElement.label : undefined;

            if (optionGroup === group && option.label === label) {
                return resolve(option.value);
            }

            if(optionGroup === undefined) {
                ungrouped.push(option.label);
            }else if(grouped[optionGroup] !== undefined){
                grouped[optionGroup].push(option.label)
            }else{
                grouped[optionGroup] = [option.label];
            }
        }

        counter--;
        if (counter === 0) {
            reject(new Error(`No option with label '${label}' in a option group ${group} found in ${timeout} seconds. Available options are: not grouped: ${ungrouped.length === 0 ? '<none>' : ungrouped.join(', ')}, ${Object.keys(grouped).map(g => g + ': ' + grouped[g].join(', ')).join(', ')}`));
        } else {
            setTimeout(checkOptions, 50);
        }
    }

    checkOptions();
})